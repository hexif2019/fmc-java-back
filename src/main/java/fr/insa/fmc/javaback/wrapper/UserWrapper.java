package fr.insa.fmc.javaback.wrapper;

public class UserWrapper {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private ResidenceWrapper residence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ResidenceWrapper getResidence() {
        return residence;
    }

    public void setResidence(ResidenceWrapper residence) {
        this.residence = residence;
    }
}
