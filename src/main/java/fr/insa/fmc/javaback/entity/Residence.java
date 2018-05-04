package fr.insa.fmc.javaback.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Set;

@Document(collection="ColResidence")
public class Residence {
    @Id
    private String id;
    private String adresse;
    private Position position;
    private Set<String> idClient;
    private Map<String, Casier> casiers;
    private Set<String> idMagasins;
    private String ville;
    private String codePostal;
    private String img;

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Set<String> getIdClient() {
        return idClient;
    }

    public void setIdClient(Set<String> idClient) {
        this.idClient = idClient;
    }

    public Map<String, Casier> getCasiers() {
        return casiers;
    }

    public void setCasiers(Map<String, Casier> casiers) {
        this.casiers = casiers;
    }

    public Set<String> getIdMagasins() {
        return idMagasins;
    }

    public void setIdMagasins(Set<String> idMagasins) {
        this.idMagasins = idMagasins;
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

    public void addMagasin(String idMagasin){
        this.idMagasins.add(idMagasin);
    }

    public void addClient(String clientId){
        this.idClient.add(clientId);
    }
}
