package fr.insa.fmc.javaback.wrapper;

import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.entity.Magasin;
import fr.insa.fmc.javaback.entity.Residence;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CommandeWrapperResidence {
    private String id;
    private String etat;
    private int prix;
    private String heureLivraison;
    private List<MagasinWrapper> magasins;
    private String userid;
    private Optional<UserWrapper> user;
    private ResidenceWrapper2 residence;

    public ResidenceWrapper2 getResidence() {
        return residence;
    }

    public void setResidence(ResidenceWrapper2 residence) {
        this.residence = residence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public List<MagasinWrapper> getMagasins() {
        return magasins;
    }

    public void setMagasins(List<MagasinWrapper> magasins) {
        this.magasins = magasins;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Optional<UserWrapper> getUser() {
        return user;
    }

    public void setUser(Optional<UserWrapper> user) {
        this.user = user;
    }

    public String getHeureLivraison() {
        return heureLivraison;
    }

    public void setHeureLivraison(String heureLivraison) {
        this.heureLivraison = heureLivraison;
    }

    public CommandeWrapperResidence(Commande commande, Residence residence) {
        this.id = commande.getId();
        this.etat = commande.getEtat().toString();
        this.prix = commande.getPrixTotal();
        this.magasins = new ArrayList<MagasinWrapper>();
        for(int i = 0; i < commande.getMagasinsCommande().size(); i++) {
            this.magasins.add(new MagasinWrapper(commande.getMagasinsCommande().get(i)));
        }
        this.userid = commande.getIdClient();
        this.residence = new ResidenceWrapper2(residence);
    }

    public CommandeWrapperResidence() {}



}
