package fr.insa.fmc.javaback.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

@Document(collection="ColClient")
public class Client {

    @Id
    private Long id;
    private String email;
    private String nom;
    private String prenom;
    private String adresse;
    private String mdp;
    private String cartePaiement;
    private Set<Long> commandesFinis;
    private Map<Long ,Commande> commandesCours;
    private Long residence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Long> getCommandesFinis() {
        return commandesFinis;
    }

    public void setCommandesFinis(Set<Long> commandesFinis) {
        this.commandesFinis = commandesFinis;
    }

    public Map<Long, Commande> getCommandesCours() {
        return commandesCours;
    }

    public void setCommandesCours(Map<Long, Commande> commandesCours) {
        this.commandesCours = commandesCours;
    }

    public Long getResidence() {
        return residence;
    }

    public void setResidence(Long residence) {
        this.residence = residence;
    }

    public Client(String email, String nom, String prenom, String mdp) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
    }
}
