package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Coursier;
import fr.insa.fmc.javaback.repository.CoursierRepository;
import fr.insa.fmc.javaback.wrapper.AuthentificationCoursierResponseWrapper;
import fr.insa.fmc.javaback.wrapper.AuthentificationWrapper;
import fr.insa.fmc.javaback.wrapper.LivreurWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CousierController {
    @Autowired
    CoursierRepository coursierRepository;

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
}
