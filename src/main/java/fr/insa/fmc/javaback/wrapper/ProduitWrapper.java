package fr.insa.fmc.javaback.wrapper;

import fr.insa.fmc.javaback.entity.ProduitsCommande;

public class ProduitWrapper {

    private String id;
    private String denomination;
    private String description;
    private int nb;
    private int prix;
    private int poids;
    private String img;
    private int volume;
    private int longueur;
    private int largeur;
    private int hauteur;

    public ProduitWrapper(ProduitsCommande produitsCommande) {
        this.id = produitsCommande.getIdProduit();
        this.denomination = produitsCommande.getDenomination();
        this.description = produitsCommande.getDescription();
        this.nb = produitsCommande.getQuantite();
        this.prix = produitsCommande.getPrixUnitaire();
        this.poids = produitsCommande.getPoidsUnitaire();
        this.img = produitsCommande.getImg();
        this.volume = produitsCommande.getVolumeUnitaire();
        this.longueur = produitsCommande.getLongueurUnitaire();
        this.largeur = produitsCommande.getLargeurUnitaire();
        this.hauteur = produitsCommande.getHauteurUnitaire();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
}
