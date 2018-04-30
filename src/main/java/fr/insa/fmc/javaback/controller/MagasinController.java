package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Magasin;
import fr.insa.fmc.javaback.entity.Produit;
import fr.insa.fmc.javaback.repository.MagasinRepository;
import fr.insa.fmc.javaback.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MagasinController {
    @Autowired
    MagasinRepository magasinRepository;
    @Autowired
    ProduitRepository produitRepository;

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
}
