package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.entity.Coursier;
import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.repository.CoursierRepository;
import fr.insa.fmc.javaback.service.PaypalService;
import fr.insa.fmc.javaback.wrapper.AuthentificationCoursierResponseWrapper;
import fr.insa.fmc.javaback.wrapper.AuthentificationWrapper;
import fr.insa.fmc.javaback.wrapper.CommandeWrapper;
import fr.insa.fmc.javaback.wrapper.LivreurWrapper;
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
    PaypalService paypalService;

    @RequestMapping(method=RequestMethod.POST, value="/coursier")
    public Coursier saveCoursier(@RequestBody Coursier coursier) {
        coursierRepository.save(coursier);
        return coursier;
    }

    @RequestMapping(method=RequestMethod.POST,value="api/livreur/authenticate",consumes="appplication/json")
    public AuthentificationCoursierResponseWrapper connectionCoursier(@RequestBody AuthentificationWrapper params){
        String email = params.getEmail();
        String mdp = params.getPassword();
        Coursier coursier = coursierRepository.connectionQuery(email,mdp);
        if(coursier==null) throw new NullPointerException("le coursier est introuvable");
        AuthentificationCoursierResponseWrapper authCoursierResponse = new AuthentificationCoursierResponseWrapper();
        String token = "token sama";
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

    @RequestMapping(method=RequestMethod.POST,value="api/livreur/terminerlivraison/{commandeId}",consumes="application/json")
    public String terminerLivraison(@PathVariable String commandeId){
        Optional<Commande> optCom = commandeRepository.findById(commandeId);
        if(optCom.isPresent()){
            Commande commande = optCom.get();
            commande.setEtat(enumEtatCommande.DANS_CASIER);
        }else{
            return "failed:no such commande in db";
        }
        return "";
    }

    @RequestMapping(method=RequestMethod.POST,value="api/livreur/getCommandesEnCour/{coursierId}")
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

    @RequestMapping(method=RequestMethod.POST,value="api/livreur/getCommandesArchiver/{userid}")
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
}
