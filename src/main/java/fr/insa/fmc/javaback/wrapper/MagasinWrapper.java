package fr.insa.fmc.javaback.wrapper;

import fr.insa.fmc.javaback.entity.Magasin;
import fr.insa.fmc.javaback.entity.MagasinsCommande;
import fr.insa.fmc.javaback.entity.Position;
import fr.insa.fmc.javaback.entity.Produit;

import java.util.ArrayList;

public class MagasinWrapper {
    private String id;
    private String adresse;
    private String description;
    private String denomination;
    private String email;
    private ArrayList<ProduitWrapper> produits;
    private String ville;
    private String codePostal;
    private String img;
    private Position position;

    public MagasinWrapper(MagasinsCommande magasinsCommande) {
        this.id = magasinsCommande.getIdMagasin();
        this.adresse = magasinsCommande.getAdresse();
        this.description = magasinsCommande.getDescription();
        this.denomination = magasinsCommande.getDenomination();
        this.email = magasinsCommande.getEmail();
        for(int i = 0; i < magasinsCommande.getProduitsCommande().size(); i++) {
            this.produits.add(new ProduitWrapper(magasinsCommande.getProduitsCommande().get(i)));
        }
        this.ville = magasinsCommande.getVille();
        this.codePostal = magasinsCommande.getCodePostal();
        this.img = magasinsCommande.getImg();
        this.position = magasinsCommande.getPosition();
    }

    public MagasinWrapper(Magasin magasin) {
        this.id = magasin.getId();
        this.adresse = magasin.getAdresse();
        this.description = magasin.getDescription();
        this.denomination = magasin.getDenomination();
        this.email = magasin.getEmail();
        for(Produit product: magasin.getProduitsList().values()) {
            this.produits.add(new ProduitWrapper(product));
        }
        this.ville = magasin.getVille();
        this.codePostal = magasin.getCodePostal();
        this.img = magasin.getImg();
        this.position = magasin.getPosition();
    }

    public MagasinWrapper() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
