package fr.insa.fmc.javaback.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

@Document(collection="ColCoursier")
public class Coursier {
    @Id
    private Long id;
    private String email;
    private String mdp;
    private int rayon;
    private Position position;
    private Map<Long,Commande> commandesEnCours;
    private Set<Long> listResidences;
    private Set<Long> commandesFinis;
    private String nom;
    private String prenom;

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

    public Map<Long, Commande> getCommandesEnCours() {
        return commandesEnCours;
    }

    public void setCommandesEnCours(Map<Long, Commande> commandesEnCours) {
        this.commandesEnCours = commandesEnCours;
    }

    public Set<Long> getListResidences() {
        return listResidences;
    }

    public void setListResidences(Set<Long> listResidences) {
        this.listResidences = listResidences;
    }

    public Set<Long> getCommandesFinis() {
        return commandesFinis;
    }

    public void setCommandesFinis(Set<Long> commandesFinis) {
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
