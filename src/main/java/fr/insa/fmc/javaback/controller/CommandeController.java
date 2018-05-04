package fr.insa.fmc.javaback.controller;

import fr.insa.fmc.javaback.configuration.GlobalURLs;
import fr.insa.fmc.javaback.entity.*;
import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import fr.insa.fmc.javaback.entity.enums.enumEtatMagasinCommande;
import fr.insa.fmc.javaback.repository.ClientRepository;
import fr.insa.fmc.javaback.repository.CommandeRepository;
import fr.insa.fmc.javaback.repository.ResidenceRepository;
import fr.insa.fmc.javaback.wrapper.CommandeWrapper;
import fr.insa.fmc.javaback.wrapper.MagasinWrapper;
import fr.insa.fmc.javaback.wrapper.ProduitWrapper;
import jdk.nashorn.internal.objects.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
public class CommandeController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    ResidenceRepository residenceRepository;

    //remote accessible methods
    @RequestMapping(method=RequestMethod.GET,value=GlobalURLs.COMMANDE_GETPANIER)
    public CommandeWrapper getPanier(@PathVariable String id) {

        //Verif si client est nul avec try catch ou sinon en 2 etapes avec Optional
        Optional<Client> clientOpt = clientRepository.findById(id);

        if(!clientOpt.isPresent())
            throw new NullPointerException("le client est introuvable");

        Client client = clientOpt.get();

        CommandeWrapper commandeWrap;


        if(client.getCommandesEnCreation()!= null && commandeRepository.findById(client.getCommandesEnCreation()).isPresent()) {
            Optional<Commande> commandeOpt = commandeRepository.findById(client.getCommandesEnCreation());
            commandeWrap = new CommandeWrapper(commandeOpt.get());
        } else {

            Commande commande = new Commande();

            commande.setEtat(enumEtatCommande.EDITION);
            commande.setPoidsTotal(0);
            commande.setPrixTotal(0);
            commande.setVolumeTotal(0);
            commande.setIdResidence(client.getResidence());
            commande.setCasiersId(new ArrayList<>());
            commande.setIdClient(id);

            if(client.getResidence() == null) throw new NullPointerException("Client non relie a une residence");

            Optional<Residence> residence = residenceRepository.findById(client.getResidence());
            if(!residence.isPresent()) throw new NullPointerException("Residence introuvable");
            commande.setPositionLivraison(residence.get().getPosition());
            commande.setMagasinsCommande(new ArrayList<MagasinsCommande>());
            commandeRepository.save(commande);

            client.setCommandeEnCreation(commande.getId());
            clientRepository.save(client);


            commandeWrap = new CommandeWrapper(commande);
        }

        return commandeWrap;
    }

    @RequestMapping(method=RequestMethod.GET,value=GlobalURLs.COMMANDE_GETCOMMANDESENCOURS)
    public ArrayList<CommandeWrapper> getCommandesEnCour(@PathVariable String id) {

        //Verif si client est nul avec try catch ou sinon en 2 etapes avec Optional
        Client client = clientRepository.findById(id).get();

        ArrayList<CommandeWrapper> commandeWrapList = new ArrayList<>();

        //verif si liste vide

        for(String commandeId: client.getCommandesCours().keySet()) {
            Optional<Commande> commandeTempo = commandeRepository.findById(commandeId);
            if(!commandeTempo.isPresent())
                throw new NullPointerException("Une commande est introuvable");

            commandeWrapList.add(new CommandeWrapper(commandeTempo.get()));
        }

        return commandeWrapList;
    }

    @RequestMapping(method=RequestMethod.GET,value=GlobalURLs.COMMANDE_GETCOMMANDESARCHIVEES)
    public ArrayList<CommandeWrapper> getCommandesArchiver(@PathVariable String id) {
        //Verif si client est nul avec try catch ou sinon en 2 etapes avec Optional
        Client client = clientRepository.findById(id).get();

        ArrayList<CommandeWrapper> commandeWrapList = new ArrayList<>();

        //verif si liste vide ce que ca fait

        //ArrayList<Long> commandesList = new ArrayList<Long>(client);

        for(String i : client.getCommandesFinis()) {
            //Verifier si probleme requete, commande non existante en base

            commandeWrapList.add(new CommandeWrapper(commandeRepository.findById(i.toString()).get()));
        }
        return commandeWrapList;

    }

    @RequestMapping(method=RequestMethod.GET,value=GlobalURLs.COMMANDE_GETLASTCOMMANDES)
    public ArrayList<CommandeWrapper> getLastCommandes(@PathVariable String id) {
        //Verif si client est nul avec try catch ou sinon en 2 etapes avec Optional
        Client client = clientRepository.findById(id).get();

        ArrayList<CommandeWrapper> commandeWrapList = new ArrayList<>();

        //verif si liste vide ce que ca fait

        //ArrayList<Long> commandesList = new ArrayList<Long>(client);

        ArrayList<String> lastCommandesList = new ArrayList<>(client.getCommandesFinis());

        for(int i = lastCommandesList.size()-1; i >=0 && i >= lastCommandesList.size()-3; i--) {
            commandeWrapList.add(new CommandeWrapper(commandeRepository.findById(lastCommandesList.get(i)).get()));
        }

        return commandeWrapList;

    }

    @RequestMapping(method=RequestMethod.POST,value=GlobalURLs.COMMANDE_UPDATEPANIER,consumes="application/json")
    public String updatePanier(@RequestBody CommandeWrapper commandeWrap){
        String userid = commandeWrap.getUserid();

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

        ArrayList<MagasinsCommande> listMag = new ArrayList<>();

        for(int i = 0; i<commandeWrap.getMagasins().size(); i++) {

            MagasinsCommande magasin = new MagasinsCommande();
            magasin.setId(String.valueOf(i));
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
                produit.setId(String.valueOf(j));

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
        String statut = "\"ok\"";
        return statut;
    }

    //internal methods
    @RequestMapping(method=RequestMethod.POST, value="/commande")
    public Commande saveCommande(@RequestBody Commande commande) {
        commandeRepository.save(commande);
        return commande;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/commande")
    public String deleteAllCommande() {
        commandeRepository.deleteAll();
        //Optional<Client> client = clientRepository.findById(id);
        //clientRepository.delete(client);
        return "";
    }

    @RequestMapping(method= RequestMethod.GET, value="/commande")
    public Iterable<Commande> findResidence() {
        return commandeRepository.findAll();
    }

}
