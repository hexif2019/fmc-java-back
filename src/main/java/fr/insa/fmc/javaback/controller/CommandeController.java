package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.entity.MagasinsCommande;
import fr.insa.fmc.javaback.entity.ProduitsCommande;
import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import fr.insa.fmc.javaback.repository.ClientRepository;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.wrapper.CommandeWrapper;
import fr.insa.fmc.javaback.wrapper.MagasinWrapper;
import fr.insa.fmc.javaback.wrapper.ProduitWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        //TODO: verifier le prix plus loin dans la mise a jour, pour chaque produit par rapport a la BDD, pour le total pour chaque magasin et pour le total de la commande
        commande.setIdClient(commandeWrap.getUserid());

        ArrayList<MagasinsCommande> listMag = new ArrayList<MagasinsCommande>();

        for(int i = 0; i<commandeWrap.getMagasins().size(); i++) {

            MagasinsCommande magasin = new MagasinsCommande();
            magasin.setId((long) i);
            MagasinWrapper magasinWrap = commandeWrap.getMagasins().get(i);
            magasin.setAdresse(magasinWrap.getAdresse());
            magasin.setDescription(magasinWrap.getDescription());
            magasin.setDenomination(magasinWrap.getDenomination());
            magasin.setEmail(magasinWrap.getEmail());
            magasin.setPosition(magasinWrap.getPosition());
            magasin.setIdMagasin(magasinWrap.getId());
            magasin.setImg(magasinWrap.getImg());

            ArrayList<ProduitsCommande> listProd = new ArrayList<ProduitsCommande>();

            for (int j = 0; j < magasinWrap.getProduits().size(); j++) {
                ProduitsCommande produit = new ProduitsCommande();
                produit.setId((long) j);

                ProduitWrapper produitWrap = magasinWrap.getProduits().get(j);

                produit.setDenomination(produitWrap.getDenomination());
                produit.setDescription(produitWrap.getDescription());
                produit.setQuantite(produitWrap.getNb());

                //Debut Implementation si donnee unitaire stocké dans le json
                produit.setPrixUnitaire(produitWrap.getPrix());
                produit.setPrixTotal(produitWrap.getPrix()*produitWrap.getNb());
                produit.setPoidsUnitaire(produitWrap.getPoids());
                produit.setPoidsTotal(produitWrap.getPoids()*produitWrap.getNb());
                produit.setVolumeUnitaire(produitWrap.getVolume());
                produit.setVolumeTotal(produitWrap.getVolume()*produitWrap.getNb());
                //Fin Implementation si donne unitaire

                produit.setLongueurUnitaire(produitWrap.getLongueur());
                produit.setLargeurUnitaire(produitWrap.getLargeur());
                produit.setHauteurUnitaire(produitWrap.getHauteur());

                produit.setImg(produitWrap.getImg());

                produit.setIdProduit(produitWrap.getId());

                listProd.add(produit);

                //TODO: finir d ajouter element produit et l ajouter a la liste
            }
            magasin.setProduitsCommande(listProd);

            listMag.add(magasin);
        }

        commande.setMagasinsCommande(listMag);

        commandeRepository.save(commande);

        //c.setEtat(commande.getEtat());
         //Map<Long, ProduitsCommande> map = new HashMap<>();

        //c.setMagasinsCommande(map);
        String statue = "ok";
        return statue;
    }

}
