package fr.insa.fmc.javaback.wrapper;

import fr.insa.fmc.javaback.entity.Residence;

public class ResidenceWrapper {
    private String id;
    private String adresse;
    private String codePostal;
    private String ville;

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

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public ResidenceWrapper(Residence residence){
        this.id = residence.getId();
        this.adresse = residence.getAdresse();
        this.codePostal = residence.getCodePostal();
        this.ville = residence.getVille();
    }

    public ResidenceWrapper(){}
}
