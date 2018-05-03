package fr.insa.fmc.javaback.wrapper;

import fr.insa.fmc.javaback.entity.Position;
import fr.insa.fmc.javaback.entity.Residence;

public class ResidenceWrapper2 {
    private String id;
    private String adresse;
    private String codePostal;
    private String ville;
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public ResidenceWrapper2(Residence residence){
        this.id = residence.getId();
        this.adresse = residence.getAdresse();
        this.codePostal = residence.getCodePostal();
        this.ville = residence.getVille();
        this.position = residence.getPosition();
    }

    public ResidenceWrapper2(){}
}
