package fr.insa.fmc.javaback.wrapper;

import fr.insa.fmc.javaback.entity.MagasinsCommande;

import java.util.ArrayList;
import java.util.Optional;

public class CommandeWrapper {
    private Long id;
    private String etat;
    private int prix;
    private ArrayList<MagasinWrapper> magasins;
    private Long userid;
    private Optional<UserWrapper> user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ArrayList<MagasinWrapper> getMagasins() {
        return magasins;
    }

    public void setMagasins(ArrayList<MagasinWrapper> magasins) {
        this.magasins = magasins;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Optional<UserWrapper> getUser() {
        return user;
    }

    public void setUser(Optional<UserWrapper> user) {
        this.user = user;
    }
}
