package fr.insa.fmc.javaback.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

@Document(collection="ColResidence")
public class Residence {
    @Id
    private Long id;
    private String adresse;
    private Position position;
    private Set<Long> idClient;
    private Map<Long,Casier> casiers;
    private Set<Long> idMagasins;
    private String vile;
    private String codePostal;
    private String img;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Set<Long> getIdClient() {
        return idClient;
    }

    public void setIdClient(Set<Long> idClient) {
        this.idClient = idClient;
    }

    public Map<Long, Casier> getCasiers() {
        return casiers;
    }

    public void setCasiers(Map<Long, Casier> casiers) {
        this.casiers = casiers;
    }

    public Set<Long> getIdMagasins() {
        return idMagasins;
    }

    public void setIdMagasins(Set<Long> idMagasins) {
        this.idMagasins = idMagasins;
    }

    public String getVile() {
        return vile;
    }

    public void setVile(String vile) {
        this.vile = vile;
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
}
