package fr.insa.fmc.javaback.controller;


import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import java.util.List;

@RestController
public class CasierController {

    @Autowired
    private ResidenceRepository residenceRepository;
    @Autowired
    private CommandeRepository commandeRepository;

    @RequestMapping(method = RequestMethod.POST,value = "/api/casier/open",consumes = "application/json")
    public String tryOpenCasier(@RequestBody String residenceId, @RequestBody String password){
        List<Commande> commandes = commandeRepository.findByIdResidence(residenceId);
        return "";
    }
}
