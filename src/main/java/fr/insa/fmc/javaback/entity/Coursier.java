package fr.insa.fmc.javaback.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

@Document(collection="ColCoursier")
public class Coursier {
    @Id
    private String id;
    private String email;
    private String mdp;
    private int rayon;
    private Position position;
    private Map<String, Commande> commandesEnCours;
    private String listResidences;
    private Set<String> commandesFinis;
    private String nom;
    private String prenom;

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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Map<String, Commande> getCommandesEnCours() {
        return commandesEnCours;
    }

    public void setCommandesEnCours(Map<String, Commande> commandesEnCours) {
        this.commandesEnCours = commandesEnCours;
    }

    public String getListResidences() {
        return listResidences;
    }

    public void setListResidences(String listResidences) {
        this.listResidences = listResidences;
    }

    public Set<String> getCommandesFinis() {
        return commandesFinis;
    }

    public void setCommandesFinis(Set<String> commandesFinis) {
        this.commandesFinis = commandesFinis;
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
}
