package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.entity.ProduitsCommande;
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
    public String updatePanier(@RequestBody CommandeWrapper commande){
        Long userid = commande.getUserid();
        Optional<Commande> commandeOpt = commandeRepository.findById(commande.getId());
        //TODO: verifier etat commande et correspondance id user BDD et json
        //TODO: verfier userid == commandeOpt.get().getIdClient()
         Commande c = commandeOpt.get();
         //c.setEtat(commande.getEtat());
         //Map<Long, ProduitsCommande> map = new HashMap<>();

        for(int i = 0; i < commande.getMagasinsCommande().length; i++){

            //map.put(i, )
        }
        //c.setMagasinsCommande(map);

        return "todo";
    }

}
