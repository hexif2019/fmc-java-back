package fr.insa.fmc.javaback.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import fr.insa.fmc.javaback.entity.*;
import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import fr.insa.fmc.javaback.repository.ClientRepository;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.repository.CoursierRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
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
    private PaypalService paypalService;

    @RequestMapping(method = RequestMethod.POST, value = "api/pay", consumes = "application/json")
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
                commande.setCasiersId(casiersId);
                residence.setCasiers(casiers);
                residenceRepository.save(residence);
            }
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

    @RequestMapping(method = RequestMethod.POST, value = "mock/pay", consumes = "application/json")
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

    @RequestMapping(method = RequestMethod.POST, value = "api/pay/success", consumes = "application/json")
    public String proceedPayment(@RequestBody PaymentExecuteNotification authorize) throws PayPalRESTException {
        Authorization authorization = paypalService.executePaymentAndGetAuthorization(authorize.getAuthorizationId(), authorize.getPayerId());
        if (authorization.getState().equals("success")) {
            Optional<Commande> commande = commandeRepository.findById(authorize.getCommandeId());
            if (commande.isPresent()) {
                Commande in = commande.get();
                in.setAuthorizationId(authorization.getId());
                in.setEtat(enumEtatCommande.PAYEMENT_EFFECTUE);

                Optional<Client> clientOpt = clientRepository.findById(in.getIdClient());
                if (!clientOpt.isPresent()) throw new NullPointerException("client introuvable");
                Client client = clientOpt.get();
                client.setCommandeEnCreation(null);
                client.addCommandeCours(in.getId(), in);

                clientRepository.save(client);

                commandeRepository.save(in);

            }
            return "success";
        }
        return "redirect:/";


    }

    @RequestMapping(method = RequestMethod.POST, value = "api/pay/cancel", consumes = "application/json")
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
}
