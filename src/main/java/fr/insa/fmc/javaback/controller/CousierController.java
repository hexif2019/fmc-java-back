package fr.insa.fmc.javaback.controller;

import com.paypal.base.rest.PayPalRESTException;
import fr.insa.fmc.javaback.configuration.GlobalURLs;
import fr.insa.fmc.javaback.entity.Client;
import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.entity.Coursier;
import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import fr.insa.fmc.javaback.repository.ClientRepository;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.repository.CoursierRepository;
import fr.insa.fmc.javaback.service.GenerationService;
import fr.insa.fmc.javaback.service.PaypalService;
import fr.insa.fmc.javaback.wrapper.AuthentificationCoursierResponseWrapper;
import fr.insa.fmc.javaback.wrapper.AuthentificationWrapper;
import fr.insa.fmc.javaback.wrapper.CommandeWrapper;
import fr.insa.fmc.javaback.wrapper.LivreurWrapper;
import jdk.nashorn.internal.objects.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    PaypalService paypalService;


    @RequestMapping(method=RequestMethod.POST,value="api/livreur/authenticate",consumes="application/json")

    //remote accessible methods
    @RequestMapping(method=RequestMethod.POST,value=GlobalURLs.COURSIER_AUTHENTICATE,consumes="appplication/json")

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
    public ArrayList<CommandeWrapper> getCommandesEnCour(@PathVariable String coursierId){
        Optional<Coursier> coursier = coursierRepository.findById(coursierId);
        if(!coursier.isPresent())
            throw new NullPointerException("Couriser introuvable");

        ArrayList<CommandeWrapper> commandeWrap = new ArrayList<CommandeWrapper>();

        for(String commandeId: coursier.get().getCommandesEnCours().keySet()) {
            Optional<Commande> commandeTempo = commandeRepository.findById(commandeId);
            if(!commandeTempo.isPresent())
                throw new NullPointerException("Une commande est introuvable");
            commandeWrap.add(new CommandeWrapper(commandeTempo.get()));
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

    //internal methods
    @RequestMapping(method=RequestMethod.POST, value="/coursier")
    public Coursier saveCoursier(@RequestBody Coursier coursier) {
        coursierRepository.save(coursier);
        return coursier;
    }
}
