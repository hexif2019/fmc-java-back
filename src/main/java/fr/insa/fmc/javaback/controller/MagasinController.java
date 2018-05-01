package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Magasin;
import fr.insa.fmc.javaback.entity.Produit;
import fr.insa.fmc.javaback.entity.Residence;
import fr.insa.fmc.javaback.repository.MagasinRepository;
import fr.insa.fmc.javaback.repository.ProduitRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
import fr.insa.fmc.javaback.wrapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MagasinController {
    @Autowired
    MagasinRepository magasinRepository;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    ResidenceRepository residenceRepository;

    @RequestMapping(method= RequestMethod.GET, value="/api/getItemMagasin/{id}")
    public Iterable<Produit> findProduitByMagasinId(@PathVariable Long id){
        Optional <Magasin> m = magasinRepository.findById(id);
        Magasin magasin = new Magasin();
        if(magasin==null){
            //TODO:Exception
        } else {
            magasin = m.get();
        }
        return magasin.getProduitsList().values();
    }

    @RequestMapping(method=RequestMethod.POST,value="api/registerMarchand",consumes="application/json")
    public RegistrationMarchandResponseWrapper registerMarchand(@RequestBody RegisterMarchandWrapper params){
        String mdp = params.getPassword();
        MarchandWrapper marchand = params.getMarchand();
        String token = "blabla";
        Magasin magasin = new Magasin();
        magasin.setId(marchand.getId());
        magasin.setAdresse(marchand.getAdresse());
        magasin.setDescription(marchand.getDescription());
        magasin.setEmail(marchand.getEmail());
        magasin.setMdp(mdp);
        magasin.setVille(marchand.getVille());
        magasin.setCodePostal(marchand.getCodePostal());
        magasinRepository.save(magasin);
        RegistrationMarchandResponseWrapper registrationMarchandResponse = new RegistrationMarchandResponseWrapper();
        registrationMarchandResponse.setToken(token);
        registrationMarchandResponse.setMarchand(marchand);
        return registrationMarchandResponse;

    }

    @RequestMapping(method=RequestMethod.POST,value="api/authenticateMarchand",consumes="application/json")
    public AuthentificationMarchandResponseWrapper connectionMarchand(@RequestBody AuthentificationWrapper params){
        String email = params.getEmail();
        String mdp = params.getPassword();
        Magasin magasin = magasinRepository.connectionQuery(email,mdp);
        AuthentificationMarchandResponseWrapper authResponse = new AuthentificationMarchandResponseWrapper();
        String token = "je suis le token";
        authResponse.setToken(token);
        MarchandWrapper marchand = new MarchandWrapper();
        marchand.setId(magasin.getId());
        marchand.setAdresse(magasin.getAdresse());
        marchand.setDescription(magasin.getDescription());
        marchand.setDenomination(magasin.getDenomination());
        marchand.setEmail(magasin.getAdresse());
        marchand.setVille(magasin.getVille());
        marchand.setCodePostal(magasin.getCodePostal());
        authResponse.setMarchand(marchand);
        return authResponse;
    }
}
