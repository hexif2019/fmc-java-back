package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Produit;
import fr.insa.fmc.javaback.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ProduitController {
    @Autowired
    ProduitRepository produitRepository;

    @RequestMapping(method= RequestMethod.GET, value="/produit")
    public Iterable<Produit> findProduit() {
        return produitRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/produit")
    public Produit saveProduit(@RequestBody Produit produit) {
        produitRepository.save(produit);
        return produit;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/produit/{id}")
    public String deleteProduitById(@PathVariable String id) {
        produitRepository.deleteById(id);
        return "";
    }
}
