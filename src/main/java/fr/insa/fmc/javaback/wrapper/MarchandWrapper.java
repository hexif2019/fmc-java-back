package fr.insa.fmc.javaback.wrapper;

import fr.insa.fmc.javaback.entity.Commande;
import fr.insa.fmc.javaback.entity.Produit;

import java.util.ArrayList;

public class MarchandWrapper {
    private String id;
    private String adresse;
    private String description;
    private String denomination;
    private String email;
    private String ville;
    private String codePostal;
    private ArrayList<ProduitWrapper> produits;
    private ArrayList<CommandeWrapper> commandes;

    public ArrayList<ProduitWrapper> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<ProduitWrapper> produits) {
        this.produits = produits;
    }

    public ArrayList<CommandeWrapper> getCommandes() {
        return commandes;
    }

    public void setCommandes(ArrayList<CommandeWrapper> commandes) {
        this.commandes = commandes;
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

    public void addCommande(Commande commande) {
        this.commandes.add(new CommandeWrapper(commande));
    }

    public void addProduit(Produit produit) {
        this.produits.add(new ProduitWrapper(produit));
    }

}
