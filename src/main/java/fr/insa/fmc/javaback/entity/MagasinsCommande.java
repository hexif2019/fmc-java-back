package fr.insa.fmc.javaback.entity;

import fr.insa.fmc.javaback.entity.enums.enumEtatMagasinCommande;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class MagasinsCommande {
    @Id
    private Long id;
    private String adresse;
    private String description;
    private String denomination;
    private String email;
    private Position position;
    private ArrayList<ProduitsCommande> produitsCommande;
    private Long idMagasin;
    private String img;
    private enumEtatMagasinCommande etatMagasinCommande;
    private String ville;
    private String codePostal;

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

    public enumEtatMagasinCommande getEtatMagasinCommande() {
        return etatMagasinCommande;
    }

    public void setEtatMagasinCommande(enumEtatMagasinCommande etatMagasinCommande) {
        this.etatMagasinCommande = etatMagasinCommande;
    }

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



    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ArrayList<ProduitsCommande> getProduitsCommande() {
        return produitsCommande;
    }

    public void setProduitsCommande(ArrayList<ProduitsCommande> produitsCommande) {
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
