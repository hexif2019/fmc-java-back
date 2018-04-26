package fr.insa.fmc.javaback.entity;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class MagasinsCommande {
    @Id
    private Long id;
    private String adresse;
    private String description;
    private String denomination;
    private String email;
    private String mdp;
    private Position position;
    private Map<Long, Produit> produitsCommande;
    private Long idMagasin;
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

    public Map<Long, Produit> getProduitsCommande() {
        return produitsCommande;
    }

    public void setProduitsCommande(Map<Long, Produit> produitsCommande) {
        this.produitsCommande = produitsCommande;
    }

    public Long getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Long idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
