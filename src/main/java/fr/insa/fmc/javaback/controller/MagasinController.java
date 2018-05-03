package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Magasin;
import fr.insa.fmc.javaback.entity.Produit;
import fr.insa.fmc.javaback.entity.Residence;
import fr.insa.fmc.javaback.repository.MagasinRepository;
import fr.insa.fmc.javaback.repository.ProduitRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
import fr.insa.fmc.javaback.service.GenerationService;
import fr.insa.fmc.javaback.wrapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class MagasinController {
    @Autowired
    MagasinRepository magasinRepository;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    ResidenceRepository residenceRepository;

    @RequestMapping(method=RequestMethod.GET, value="/magasin")
    public Iterable<Magasin> findClient() {
        return magasinRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/magasin")
    public Magasin saveMagasin(@RequestBody Magasin magasin) {
        magasinRepository.save(magasin);
        return magasin;
    }

    @RequestMapping(method= RequestMethod.GET, value="/api/getProduits/{id}")
    public Iterable<Produit> findProduitByMagasinId(@PathVariable String id){
        Optional <Magasin> m = magasinRepository.findById(id);
        Magasin magasin = new Magasin();
        if(m.isPresent()){
            magasin = m.get();
        } else {
            throw new NullPointerException("Magasin introuvable");
        }
        return magasin.getProduitsList().values();
    }

    @RequestMapping(method=RequestMethod.GET,value="/api/getProduit/{idmagasin}/{idproduit}")
    public Produit findProduitByMagainIdAndProduitId(@PathVariable String idmagasin, String idproduit){
        Optional <Magasin> m = magasinRepository.findById(idmagasin);
        Magasin magasin = new Magasin();
        if(m.isPresent()){
            magasin=m.get();
        } else {
            throw new NullPointerException("magasin introuvable");
        }
        Produit produit = magasin.getProduitsList().get(idproduit);
        if(produit == null){
            throw new NullPointerException("produit non trouvable");
        }
        return produit;
    }



    @RequestMapping(method=RequestMethod.POST,value="api/registerMarchand",consumes="application/json")
    public RegistrationMarchandResponseWrapper registerMarchand(@RequestBody RegisterMarchandWrapper params) throws Exception{
        String mdp = params.getPassword();
        if(mdp.isEmpty()){
            throw new Exception("you must assign a password");
        }
        MarchandWrapper marchand = params.getMarchand();
        String token = GenerationService.GenerateToken();
        //String token = "je suis un token" ;
        Magasin magasin = new Magasin();
        //magasin.setId(marchand.getId());
        magasin.setAdresse(marchand.getAdresse());
        magasin.setDescription(marchand.getDescription());
        magasin.setEmail(marchand.getEmail());
        magasin.setDenomination(marchand.getDenomination());
        if(magasinRepository.findMagasinByEmail(marchand.getEmail())!=null){
            throw new Exception("Cet adresse email existe deja");
        }
        magasin.setMdp(mdp);
        magasin.setVille(marchand.getVille());
        magasin.setCodePostal(marchand.getCodePostal());
        magasin.setProduitsList(new HashMap<String, Produit>());
        magasinRepository.save(magasin);
        RegistrationMarchandResponseWrapper registrationMarchandResponse = new RegistrationMarchandResponseWrapper();
        marchand.setId(magasin.getId());
        registrationMarchandResponse.setToken(token);
        registrationMarchandResponse.setMarchand(marchand);
        return registrationMarchandResponse;

    }

    @RequestMapping(method=RequestMethod.POST,value="api/authenticateMarchand",consumes="application/json")
    public AuthentificationMarchandResponseWrapper connectionMarchand(@RequestBody AuthentificationWrapper params){
        String email = params.getEmail();
        String mdp = params.getPassword();
        Magasin magasin = magasinRepository.connectionQuery(email,mdp);
        if(magasin==null){
            throw new NullPointerException("Le magasin est introuvable");
        }
        AuthentificationMarchandResponseWrapper authResponse = new AuthentificationMarchandResponseWrapper();
        String token = GenerationService.GenerateToken();
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

    @RequestMapping(method=RequestMethod.POST,value="api/updateproduit",consumes="application/json")
    public String updateProduit(@RequestBody ProduitMagWrapper params) {
        String magasinId = params.getIdMagasin();

        Optional<Magasin> magasinOpt = magasinRepository.findById(magasinId);

        if(!magasinOpt.isPresent())
            throw new NullPointerException("Le magasin est introuvable");

        Magasin magasin = magasinOpt.get();

        ProduitWrapper produitWrap = params.getProduit();

        Produit product = new Produit();

        if(params.getProduit().getId() == null){
            product = new Produit(produitWrap);
            String token = "1";
            int i = 1;
            while(magasin.getProduitsList().get(token) != null) {
                i++;
                token = String.valueOf(i);
            }
            //TODO: getToken
            product.setId(token);
        } else {
            product = magasin.getProduitsList().get(params.getProduit().getId());


            if(product == null)
                throw new NullPointerException("Aucun produit avec cet ID n est present dans le magasin");

            product.setDenomination(produitWrap.getDenomination());
            product.setDescription(produitWrap.getDescription());
            product.setPrix(produitWrap.getPrix());
            product.setPoids(produitWrap.getPoids());
            product.setImg(produitWrap.getImg());
            product.setVolume(produitWrap.getVolume());
            product.setLongeur(produitWrap.getLongueur());
            product.setLargeur(produitWrap.getLargeur());
            product.setHauteur(produitWrap.getHauteur());
        }

        magasin.addProduit(product);

        magasinRepository.save(magasin);


        return "ok";
    }
}
