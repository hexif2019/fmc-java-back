package fr.insa.fmc.javaback.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

@Document(collection="ColClient")
public class Client {

    @Id
    private String id;
    private String email;
    private String nom;
    private String prenom;
    private String adresse;
    private String mdp;
    private String cartePaiement;
    private Set<String> commandesFinis;
    private Map<String, Commande> commandesCours;
    private String residence;
    private String commandeEnCreation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getCartePaiement() {
        return cartePaiement;
    }

    public void setCartePaiement(String cartePaiement) {
        this.cartePaiement = cartePaiement;
    }

    public Set<String> getCommandesFinis() {
        return commandesFinis;
    }

    public void setCommandesFinis(Set<String> commandesFinis) {
        this.commandesFinis = commandesFinis;
    }

    public Map<String, Commande> getCommandesCours() {
        return commandesCours;
    }

    public void setCommandesCours(Map<String, Commande> commandesCours) {
        this.commandesCours = commandesCours;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getCommandesEnCreation() {
        return commandeEnCreation;
    }

    public void setCommandeEnCreation(String commandeEnCreation) {
        this.commandeEnCreation = commandeEnCreation;
    }

    public void addCommandeCours(String idCommandeCours, Commande CommandeCours) {this.commandesCours.put(idCommandeCours, CommandeCours);}


    public Client(String email, String nom, String prenom, String mdp) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
    }
    public Client(){

    }
}
