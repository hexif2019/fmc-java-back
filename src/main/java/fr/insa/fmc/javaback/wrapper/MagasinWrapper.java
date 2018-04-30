package fr.insa.fmc.javaback.wrapper;

import fr.insa.fmc.javaback.entity.Position;

import java.util.ArrayList;

public class MagasinWrapper {
    private Long id;
    private String adresse;
    private String description;
    private String denomination;
    private String email;
    private ArrayList<ProduitWrapper> produits;
    private String ville;
    private String codePostal;
    private String img;
    private Position position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<ProduitWrapper> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<ProduitWrapper> produits) {
        this.produits = produits;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
