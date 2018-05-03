package fr.insa.fmc.javaback.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

@Document(collection="ColMagasin")
public class Magasin {
    @Id
    private String id;
    private String adresse;
    private String description;
    private String denomination;
    private String email;
    private String mdp;
    private Position position;
    private Map<String, Produit> produitsList; // l'index est l'id du produit
    private String ville;
    private String codePostal;
    private String img;
    private Set<String> idCommandes;

    public Set<String> getIdCommandes() {
        return idCommandes;
    }

    public void setIdCommandes(Set<String> idCommandes) {
        this.idCommandes = idCommandes;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Map<String, Produit> getProduitsList() {
        return produitsList;
    }

    public void setProduitsList(Map<String, Produit> produitsList) {
        this.produitsList = produitsList;
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

    public void addProduit(Produit produit){
        produitsList.put(produit.getId(), produit);
    }
}
