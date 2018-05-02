package fr.insa.fmc.javaback.entity;

import fr.insa.fmc.javaback.wrapper.ProduitWrapper;
import org.springframework.data.annotation.Id;

public class Produit {
    @Id
    private String id;
    private String denomination;
    private String description;
    private int prix;
    private int poids;
    private int volume;
    private int longeur;
    private int largeur;
    private int hauteur;
    private String img;

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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getLongeur() {
        return longeur;
    }

    public void setLongeur(int longeur) {
        this.longeur = longeur;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Produit(ProduitWrapper produitWrap){
        this.denomination = produitWrap.getDenomination();
        this.description = produitWrap.getDescription();
        this.prix = produitWrap.getPrix();
        this.poids = produitWrap.getPoids();
        this.volume = produitWrap.getVolume();
        this.longeur = produitWrap.getLongueur();
        this.largeur = produitWrap.getLargeur();
        this.hauteur = produitWrap.getHauteur();
        this.img = produitWrap.getImg();
    }

    public Produit() {}


}
