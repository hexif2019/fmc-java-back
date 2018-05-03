package fr.insa.fmc.javaback.controller;

import com.paypal.base.rest.PayPalRESTException;
import fr.insa.fmc.javaback.configuration.GlobalURLs;
import fr.insa.fmc.javaback.entity.*;
import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import fr.insa.fmc.javaback.entity.enums.enumEtatMagasinCommande;
import fr.insa.fmc.javaback.repository.*;
import fr.insa.fmc.javaback.service.GenerationService;
import fr.insa.fmc.javaback.service.PaypalService;
import fr.insa.fmc.javaback.wrapper.*;
import jdk.nashorn.internal.objects.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CousierController {
    @Autowired
    CoursierRepository coursierRepository;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    MagasinRepository magasinRepository;
    @Autowired
    ResidenceRepository residenceRepository;
    @Autowired
    PaypalService paypalService;



    //remote accessible methods
    @RequestMapping(method=RequestMethod.POST,value=GlobalURLs.COURSIER_AUTHENTICATE,consumes="application/json")

    public AuthentificationCoursierResponseWrapper connectionCoursier(@RequestBody AuthentificationWrapper params){
        String email = params.getEmail();
        String mdp = params.getPassword();
        Coursier coursier = coursierRepository.connectionQuery(email,mdp);
        if(coursier==null) throw new NullPointerException("le coursier est introuvable");
        AuthentificationCoursierResponseWrapper authCoursierResponse = new AuthentificationCoursierResponseWrapper();
        String token = GenerationService.GenerateToken();
        authCoursierResponse.setToken(token);
        LivreurWrapper livreur = new LivreurWrapper();
        livreur.setId(coursier.getId());
        livreur.setEmail(coursier.getEmail());
        livreur.setNom(coursier.getNom());
        livreur.setPrenom(coursier.getPrenom());
        livreur.setRayon(coursier.getRayon());
        livreur.setPosition(coursier.getPosition());
        authCoursierResponse.setUser(livreur);
        return authCoursierResponse;
    }

    @RequestMapping(method=RequestMethod.GET,value=GlobalURLs.COURSIER_TERMINERLIVRAISON)
    public String terminerLivraison(@PathVariable String commandeId) throws PayPalRESTException {
        Optional<Commande> optCom = commandeRepository.findById(commandeId);
        if(optCom.isPresent()){
            Commande commande = optCom.get();
            commande.setEtat(enumEtatCommande.DANS_CASIER);
            //capture de l'authorisation
            paypalService.captureAuthorization(commande.getAuthorizationId(),PaypalService.ConvertIntToDouble(commande.getPrixTotal()));
            commandeRepository.save(commande);
            //sauvegarde de l'idCommande dans le client
            Client client = clientRepository.findById(commande.getIdClient()).get();
            client.getCommandesFinis().add(commandeId);
            clientRepository.save(client);
        }else{
            return "failed:no such commande in db";
        }
        return "";
    }

    @RequestMapping(method=RequestMethod.GET,value=GlobalURLs.COURSIER_GETCOMMANDESENCOURS)
    public ArrayList<CommandeWrapperResidence> getCommandesEnCour(@PathVariable String coursierId){
        Optional<Coursier> coursier = coursierRepository.findById(coursierId);
        if(!coursier.isPresent())
            throw new NullPointerException("Couriser introuvable");

        ArrayList<CommandeWrapperResidence> commandeWrap = new ArrayList<CommandeWrapperResidence>();

        for(String commandeId: coursier.get().getCommandesEnCours().keySet()) {
            Optional<Commande> commandeTempo = commandeRepository.findById(commandeId);
            if(!commandeTempo.isPresent())
                throw new NullPointerException("Une commande est introuvable");
            Optional<Residence> residenceTempo = residenceRepository.findById(commandeTempo.get().getIdResidence());
            if(!residenceTempo.isPresent())
                throw new NullPointerException("Une residence est introuvable");
            commandeWrap.add(new CommandeWrapperResidence(commandeTempo.get(), residenceTempo.get()));
        }
        return commandeWrap;

    }

    @RequestMapping(method=RequestMethod.GET,value=GlobalURLs.COURSIER_GETCOMMANDESARCHIVEES)
    public ArrayList<CommandeWrapper> getCommandesArchiver(@PathVariable String coursierId){
        Optional<Coursier> coursier = coursierRepository.findById(coursierId);
        if(!coursier.isPresent())
            throw new NullPointerException("Couriser introuvable");

        ArrayList<CommandeWrapper> commandeWrap = new ArrayList<CommandeWrapper>();

        for(String commandeId: coursier.get().getCommandesFinis()) {
            Optional<Commande> commandeTempo = commandeRepository.findById(commandeId);

            if(!commandeTempo.isPresent())
                throw new NullPointerException("Une commande est introuvable");

            commandeWrap.add(new CommandeWrapper(commandeTempo.get()));
        }

        return commandeWrap;

    }

    @RequestMapping(method = RequestMethod.GET,value=GlobalURLs.COURSIER_ONMAGASIN)
    public String updateCheckpointMagasins(@PathVariable String commandeId,@PathVariable String marchandId){
        Optional<Magasin> magasinOpt = magasinRepository.findById(marchandId);
        Optional<Commande> commandeOpt = commandeRepository.findById(commandeId);
        boolean firstTimePassage = true;
        if(!magasinOpt.isPresent()) throw new NullPointerException("marchand introuvable");
        if(!commandeOpt.isPresent()) throw new NullPointerException("coursier introuvable");
        Magasin magasin = magasinOpt.get();
        Commande commande = commandeOpt.get();
        List<MagasinsCommande> magasinsCommandes = commande.getMagasinsCommande();
        for(MagasinsCommande mc : magasinsCommandes){
            if(mc.getEtatMagasinCommande()== enumEtatMagasinCommande.EN_COURS_DE_LIVRAISON) firstTimePassage = false;
            if(mc.getIdMagasin().equals(marchandId)) mc.setEtatMagasinCommande(enumEtatMagasinCommande.EN_COURS_DE_LIVRAISON);
        }
        if(firstTimePassage) commande.setEtat(enumEtatCommande.EN_COURS_DE_LIVRAISON);
        return "success";
    }
    //internal methods
    @RequestMapping(method=RequestMethod.POST, value="/coursier")
    public Coursier saveCoursier(@RequestBody Coursier coursier) {
        coursierRepository.save(coursier);
        return coursier;
    }
}
