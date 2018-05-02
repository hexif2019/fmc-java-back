package fr.insa.fmc.javaback.entity;

import org.springframework.data.annotation.Id;

public class ProduitsCommande {
    @Id
    private String id;
    private String denomination;
    private String description;
    private int quantite;
    private int prixTotal;
    private int prixUnitaire;
    private int poidsTotal;
    private int poidsUnitaire;
    private int volumeTotal;
    private int volumeUnitaire;
    private int longueurUnitaire;
    private int largeurUnitaire;
    private int hauteurUnitaire;
    private String img;
    private String idProduit;

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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getPoidsTotal() {
        return poidsTotal;
    }

    public void setPoidsTotal(int poidsTotal) {
        this.poidsTotal = poidsTotal;
    }

    public int getPoidsUnitaire() {
        return poidsUnitaire;
    }

    public void setPoidsUnitaire(int poidsUnitaire) {
        this.poidsUnitaire = poidsUnitaire;
    }

    public int getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(int volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public int getVolumeUnitaire() {
        return volumeUnitaire;
    }

    public void setVolumeUnitaire(int volumeUnitaire) {
        this.volumeUnitaire = volumeUnitaire;
    }

    public int getLongueurUnitaire() {
        return longueurUnitaire;
    }

    public void setLongueurUnitaire(int longueurUnitaire) {
        this.longueurUnitaire = longueurUnitaire;
    }

    public int getLargeurUnitaire() {
        return largeurUnitaire;
    }

    public void setLargeurUnitaire(int largeurUnitaire) {
        this.largeurUnitaire = largeurUnitaire;
    }

    public int getHauteurUnitaire() {
        return hauteurUnitaire;
    }

    public void setHauteurUnitaire(int hauteurUnitaire) {
        this.hauteurUnitaire = hauteurUnitaire;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }
}
