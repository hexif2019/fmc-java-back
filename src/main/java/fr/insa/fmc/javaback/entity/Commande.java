package fr.insa.fmc.javaback.entity;

import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document(collection="ColCommande")
public class Commande {
    @Id
    private Long id;
    private Date heureCommande;
    private Date heureLivraison;
    private Date heureRecuperationParClient;
    private Double poidsTotal;
    private Double prixTotal;
    private Double volumeTotal;
    private enumEtatCommande etat;
    private Long idResidence;
    private ArrayList<Long> casiers;
    private Long idCoursier;
    private Long idClient;
    private Position positionLivraison;
    private ArrayList<MagasinsCommande> magasinsCommande;
    private String paymentId;
    private String authorizationId;


    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(String authorizationId) {
        this.authorizationId = authorizationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHeureCommande() {
        return heureCommande;
    }

    public void setHeureCommande(Date heureCommande) {
        this.heureCommande = heureCommande;
    }

    public Date getHeureLivraison() {
        return heureLivraison;
    }

    public void setHeureLivraison(Date heureLivraison) {
        this.heureLivraison = heureLivraison;
    }

    public Date getHeureRecuperationParClient() {
        return heureRecuperationParClient;
    }

    public void setHeureRecuperationParClient(Date heureRecuperationParClient) {
        this.heureRecuperationParClient = heureRecuperationParClient;
    }

    public Double getPoidsTotal() {
        return poidsTotal;
    }

    public void setPoidsTotal(Double poidsTotal) {
        this.poidsTotal = poidsTotal;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Double getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(Double volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public enumEtatCommande getEtat() {
        return etat;
    }

    public void setEtat(enumEtatCommande etat) {
        this.etat = etat;
    }

    public Long getIdResidence() {
        return idResidence;
    }

    public void setIdResidence(Long idResidence) {
        this.idResidence = idResidence;
    }

    public ArrayList<Long> getCasiers() {
        return casiers;
    }

    public void setCasiers(ArrayList<Long> casiers) {
        this.casiers = casiers;
    }

    public Long getIdCoursier() {
        return idCoursier;
    }

    public void setIdCoursier(Long idCoursier) {
        this.idCoursier = idCoursier;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Position getPositionLivraison() {
        return positionLivraison;
    }

    public void setPositionLivraison(Position positionLivraison) {
        this.positionLivraison = positionLivraison;
    }

    public ArrayList<MagasinsCommande> getMagasinsCommande() {
        return magasinsCommande;
    }

    public void setMagasinsCommande(ArrayList<MagasinsCommande> magasinsCommande) {
        this.magasinsCommande = magasinsCommande;
    }
}
