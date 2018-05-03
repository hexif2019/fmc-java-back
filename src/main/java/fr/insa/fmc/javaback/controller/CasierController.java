package fr.insa.fmc.javaback.controller;


import fr.insa.fmc.javaback.configuration.GlobalURLs;
import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
import fr.insa.fmc.javaback.wrapper.OpenCasier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CasierController {

    @Autowired
    private ResidenceRepository residenceRepository;
    @Autowired
    private CommandeRepository commandeRepository;

    @RequestMapping(method = RequestMethod.POST,value = GlobalURLs.CASIER_OPEN, consumes = "application/json")
    public List<String> tryOpenCasier(@RequestBody OpenCasier openCasier){
        List<String> casiersAOuvrir = new ArrayList<>();
        List<Commande> commandes = commandeRepository.findByIdResidence(openCasier.getResidenceId());
        for(Commande c : commandes){
            if(c.getMdpCoursier().equals(openCasier.getPassword())){
                casiersAOuvrir = c.getCasiersId();
                c.setMdpCoursier(null);
                commandeRepository.save(c);
                break;
            }
            if(c.getMdpClient().equals(openCasier.getPassword())){
                if(c.getMdpCoursier()==null){
                    c.setMdpClient(null);
                }
                casiersAOuvrir = c.getCasiersId();
                commandeRepository.save(c);
                break;
            }
        }
        return casiersAOuvrir;
    }
}
