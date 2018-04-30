package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.entity.ProduitsCommande;
import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import fr.insa.fmc.javaback.repository.ClientRepository;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.wrapper.CommandeWrapper;
import fr.insa.fmc.javaback.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class CommandeController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @RequestMapping(method= RequestMethod.GET, value="/commande")
    public Iterable<Commande> findResidence() {
        return commandeRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST,value="/api/updatePanier/{userid}")
    public String updatePanier(@RequestBody CommandeWrapper commandeWrap){
        Long userid = commandeWrap.getUserid();
        Optional<Commande> commandeOpt = commandeRepository.findById(commandeWrap.getId());

        Commande commande = commandeOpt.get();
        //TODO: verifier etat commande (en BDD et celle json) et correspondance id user BDD et json
        //TODO: verfier userid == commande.getIdClient()

        commande.setId(commandeWrap.getId());
        commande.setEtat(enumEtatCommande.valueOf(commandeWrap.getEtat()));
        commande.setPrixTotal(commandeWrap.getPrix());
        //TODO: verifier le prix plus loin dans la mise a jour
        commande.setIdClient(commandeWrap.getUserid());

        
        //commande.setMagasinsCommande(commandeWrap.getMagasinsCommande());



        //c.setEtat(commande.getEtat());
         //Map<Long, ProduitsCommande> map = new HashMap<>();

        //c.setMagasinsCommande(map);

        return "todo";
    }

}
