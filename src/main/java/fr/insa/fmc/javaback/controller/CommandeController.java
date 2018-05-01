package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.entity.Client;
import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.entity.MagasinsCommande;
import fr.insa.fmc.javaback.entity.ProduitsCommande;
import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import fr.insa.fmc.javaback.entity.enums.enumEtatMagasinCommande;
import fr.insa.fmc.javaback.repository.ClientRepository;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.wrapper.CommandeWrapper;
import fr.insa.fmc.javaback.wrapper.MagasinWrapper;
import fr.insa.fmc.javaback.wrapper.ProduitWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class CommandeController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @RequestMapping(method=RequestMethod.POST, value="/commande")
    public Commande saveCommande(@RequestBody Commande commande) {
        commandeRepository.save(commande);

        return commande;
    }

    @RequestMapping(method= RequestMethod.GET, value="/commande")
    public Iterable<Commande> findResidence() {
        return commandeRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.GET,value="/api/getPanier/{userid}")
    public CommandeWrapper getPanier(@PathVariable Long id) {

        //Verif si client est nul avec try catch ou sinon en 2 etapes avec Optional
        Client client = clientRepository.findById(id).get();

        Commande commande = commandeRepository.findById(client.getCommandesEnCreation()).get();

        CommandeWrapper commandeWrap = new CommandeWrapper(commande);

        return commandeWrap;
    }

    @RequestMapping(method=RequestMethod.GET,value="/api/getCommandesEnCour/{userid}")
    public ArrayList<CommandeWrapper> getCommandesEnCour(@PathVariable Long id) {

        //Verif si client est nul avec try catch ou sinon en 2 etapes avec Optional
        Client client = clientRepository.findById(id).get();

        ArrayList<CommandeWrapper> commandeWrapList = new ArrayList<CommandeWrapper>();

        //verif si liste vide

        ArrayList<Commande> commandesList = new ArrayList<Commande>(client.getCommandesCours().values());

        for(int i = 0; i < commandesList.size(); i++) {
            commandeWrapList.add(new CommandeWrapper(commandesList.get(i)));
        }

        return commandeWrapList;
    }

    @RequestMapping(method=RequestMethod.GET,value="/api/getCommandesArchiver/{userid}")
    public ArrayList<CommandeWrapper> getCommandesArchiver(@PathVariable Long id) {
        //Verif si client est nul avec try catch ou sinon en 2 etapes avec Optional
        Client client = clientRepository.findById(id).get();

        ArrayList<CommandeWrapper> commandeWrapList = new ArrayList<CommandeWrapper>();

        //verif si liste vide ce que ca fait

        //ArrayList<Long> commandesList = new ArrayList<Long>(client);

        for(Long i : client.getCommandesFinis()) {
            //Verifier si probleme requete, commande non existante en base
            commandeWrapList.add(new CommandeWrapper(commandeRepository.findById(i).get()));
        }

        return commandeWrapList;

    }

    @RequestMapping(method=RequestMethod.GET,value="/api/getLastCommandes/{userid}")
    public ArrayList<CommandeWrapper> getLastCommandes(@PathVariable Long id) {
        //Verif si client est nul avec try catch ou sinon en 2 etapes avec Optional
        Client client = clientRepository.findById(id).get();

        ArrayList<CommandeWrapper> commandeWrapList = new ArrayList<CommandeWrapper>();

        //verif si liste vide ce que ca fait

        //ArrayList<Long> commandesList = new ArrayList<Long>(client);

        ArrayList<Long> lastCommandesList = new ArrayList<Long>(client.getCommandesFinis());

        for(long i = lastCommandesList.size()-1; i >=0 && i >= lastCommandesList.size()-3; i--) {
            commandeWrapList.add(new CommandeWrapper(commandeRepository.findById(i).get()));
        }

        return commandeWrapList;

    }


    @RequestMapping(method=RequestMethod.POST,value="/api/updatePanier/{userid}")
    public String updatePanier(@RequestBody CommandeWrapper commandeWrap){
        Long userid = commandeWrap.getUserid();

        Optional<Commande> commandeOpt = commandeRepository.findById(commandeWrap.getId());

        //ATTENTION, si requete donne du null, remplacer
        //        Commande commande = commandeOpt.get();
        //      par
        //        Commande commande = new Commande();
        //Simple if(commandeOpt.get() == null) est-il suffisant ?
        Commande commande = commandeOpt.get();
        //TODO: verifier etat commande (en BDD et celle json) et correspondance id user BDD et json
        commande.setEtat(enumEtatCommande.EDITION);
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
            magasin.setEtatMagasinCommande(enumEtatMagasinCommande.EDITION);
            magasin.setVille(magasinWrap.getVille());
            magasin.setCodePostal(magasinWrap.getCodePostal());

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
