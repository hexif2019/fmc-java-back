package fr.insa.fmc.javaback.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import fr.insa.fmc.javaback.configuration.GlobalURLs;
import fr.insa.fmc.javaback.entity.*;
import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import fr.insa.fmc.javaback.entity.enums.enumEtatMagasinCommande;
import fr.insa.fmc.javaback.repository.*;
import fr.insa.fmc.javaback.service.*;
import fr.insa.fmc.javaback.wrapper.CasierDisponibilite;
import fr.insa.fmc.javaback.wrapper.CommandeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class PaymentController {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ResidenceRepository residenceRepository;
    @Autowired
    private CoursierRepository coursierRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MagasinRepository magasinsRepository;
    @Autowired
    private PaypalService paypalService;

    //remote accessible methods
    @RequestMapping(method = RequestMethod.POST, value = GlobalURLs.PAYPAL_PAY, consumes = "application/json")
    public PaymentCreationNotification pay(@RequestBody CommandeWrapper commandeWrap) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date dateLivraisonFormatted;
        if(commandeWrap.getHeureLivraison() == null) dateLivraisonFormatted = new Date();
        else dateLivraisonFormatted = format.parse(commandeWrap.getHeureLivraison());
        if (dateLivraisonFormatted.getDay() < new Date().getDay()) return null;
        PaymentCreationNotification res = new PaymentCreationNotification();
        try {
            String baseUrl = "client.fais-mes-courses.fr/api/pay/";
            Optional<Commande> commandeOpt = commandeRepository.findById(commandeWrap.getId());
            Commande commande;
            if (commandeOpt.isPresent()) commande = commandeOpt.get();
            else throw new NullPointerException("commande introuvable");
            //recherche de casier et livreur disponible
            //recherche casier
            Optional<Residence> residenceOpt = residenceRepository.findById(commande.getIdResidence());
            Residence residence;
            if (residenceOpt.isPresent()) residence = residenceOpt.get();
            else throw new NullPointerException("residence introuvable");
            Map<String, Casier> casiers = residence.getCasiers();
            boolean casierAddSuccess = false;
            int volumeAAttributer = commande.getVolumeTotal();
            List<String> casiersId = new ArrayList<>();
            for (Map.Entry<String, Casier> e : casiers.entrySet()) {
                List<CasierDisponibilite> lcd = CasierService.SetCasierToOccupe(dateLivraisonFormatted, e.getValue().getDisponibilites());
                if (lcd != null) {
                    e.getValue().setDisponibilites(lcd);
                    volumeAAttributer -= e.getValue().getVolume();
                    casiersId.add(e.getKey());
                    if(volumeAAttributer <=0){
                        casierAddSuccess = true;
                        break;
                    }
                }
            }
            commande.setCasiersId(casiersId);
            residence.setCasiers(casiers);
            residenceRepository.save(residence);
            if (!casierAddSuccess) return null;

            //recherche de livreur
            Optional<Client> clientOpt = clientRepository.findById(commande.getIdClient());
            if (!clientOpt.isPresent()) throw new NullPointerException("client introuvable");
            Client client = clientOpt.get();
            List<Coursier> coursiers = coursierRepository.findByResidenceId(client.getResidence());
            int index = CoursierService.attributeCommandeToCoursier(coursiers);
            coursiers.get(index).getCommandesEnCours().put(commande.getId(), commande);
            commande.setIdCoursier(coursiers.get(index).getId());
            //LIVREUR ATTRIBUE
            //set des mdps
            commande.setMdpCoursier(GenerationService.GenerateCode());
            commande.setMdpClient(GenerationService.GenerateCode());
            //mise a jour des dates
            commande.setHeureLivraison(dateLivraisonFormatted);
            commande.setHeureCommande(new Date());
            commande.setEtat(enumEtatCommande.ATTRIBUE_A_COURSIER);
            Payment payment = paypalService.createPayment(PaypalService.ConvertIntToDouble((commande.getPrixTotal()))+5.0, "Commande à régler", baseUrl + "cancel", baseUrl + "success");
            res.setPaymentID(payment.getId());
            commande.setPaymentId(payment.getId());
            commandeRepository.save(commande);
            coursierRepository.save(coursiers.get(index));
        } catch (PayPalRESTException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.POST, value = GlobalURLs.PAYPAL_PAYSUCCESS, consumes = "application/json")
    public String proceedPayment(@RequestBody PaymentExecuteNotification authorize) throws PayPalRESTException {
        Authorization authorization = paypalService.executePaymentAndGetAuthorization(authorize.getPaymentID(), authorize.getPayerID());
        if (authorization.getState().equals("authorized")) {
            Commande commande = null;
            if(authorize.getCommandeId()==null){
                List<Commande> commandes = commandeRepository.findByPaymentId(authorize.getPaymentID());
                if(!commandes.isEmpty()) commande = commandes.get(0);
            }
            else commande = commandeRepository.findById(authorize.getCommandeId()).get();
            if (commande!=null) {
                commande.setAuthorizationId(authorization.getId());
                commande.setEtat(enumEtatCommande.PAYEMENT_EFFECTUE);
                ArrayList<MagasinsCommande> magasinsCommandes = new ArrayList<MagasinsCommande>();
                ArrayList<Magasin> magasins = new ArrayList<Magasin>();
                for(MagasinsCommande magasinCommande: commande.getMagasinsCommande()) {
                    magasinCommande.setEtatMagasinCommande(enumEtatMagasinCommande.PAYEMENT_EFFECTUE);
                    magasinsCommandes.add(magasinCommande);
                    Optional<Magasin> magOptTempo = magasinsRepository.findById(magasinCommande.getIdMagasin());
                    if(!magOptTempo.isPresent()) throw new NullPointerException("Un magasin introuvable");
                    Magasin magTempo = magOptTempo.get();
                    magTempo.addCommande(commande.getId());
                    magasins.add(magTempo);
                }
                commande.setMagasinsCommande(magasinsCommandes);
                Optional<Client> clientOpt = clientRepository.findById(commande.getIdClient());
                if (!clientOpt.isPresent()) throw new NullPointerException("client introuvable");
                Client client = clientOpt.get();
                client.setCommandeEnCreation(null);
                client.addCommandeCours(commande.getId(), commande);

                for(Magasin mag: magasins) {

                    magasinsRepository.save(mag);

                }

                clientRepository.save(client);
                commandeRepository.save(commande);
            }
            return "\"success\"";
        }
        return "redirect:/";


    }

    @RequestMapping(method = RequestMethod.POST, value = GlobalURLs.PAYPAL_PAYCANCEL, consumes = "application/json")
    public String cancelPayment(@RequestBody CommandeWrapper commandeWrap) {
        Optional<Commande> commandeOpt = commandeRepository.findById(commandeWrap.getId());
        if (!commandeOpt.isPresent()) throw new NullPointerException("commande introuvable");
            Commande commande = commandeOpt.get();
        //annuler la dispo du casier
        Residence residence = residenceRepository.findById(commande.getIdResidence()).get();
        Map<String,Casier> casiers = residence.getCasiers();
        for(String id : commande.getCasiersId()){
            List<CasierDisponibilite> e =  casiers.get(id).getDisponibilites();
            e = CasierService.SetCasierToLibre(commande.getHeureLivraison(),e);
            casiers.get(id).setDisponibilites(e);
        }
        residence.setCasiers(casiers);
        commande.setCasiersId(null);
        //annuler le livreur
        Iterable<Coursier> coursiers = coursierRepository.findAll();
        for(Coursier c : coursiers){
            c.getCommandesEnCours().remove(commande.getId());
        }
        commande.setIdCoursier(null);
        //annuler les champs de commande (mdp) et mettre à annuler
        commande.setMdpClient(null);
        commande.setMdpCoursier(null);
        commande.setEtat(enumEtatCommande.ANNULATION_CLIENT);
        //save pour les entités modifiées
        residenceRepository.save(residence);
        coursierRepository.saveAll(coursiers);
        commandeRepository.save(commande);
        return "cancelled";
    }

    //internal methods
    @RequestMapping(method = RequestMethod.POST, value = GlobalURLs.MOCK_PAYPAL_PAY, consumes = "application/json")
    public PaymentCreationNotification payMock() {
        PaymentCreationNotification res = new PaymentCreationNotification();
        try {
            String baseUrl = "client.fais-mes-courses.fr/api/pay/";
            Payment payment = paypalService.createPayment(30.11, "Commande à régler", baseUrl + "cancel", baseUrl + "success");
            res.setPaymentID(payment.getId());
        } catch (PayPalRESTException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }
}
