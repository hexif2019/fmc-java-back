package fr.insa.fmc.javaback.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.insa.fmc.javaback.entity.enums.enumEtatCommande;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection="ColCommande")
public class Commande {
    @Id
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date heureCommande;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date heureLivraison;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date heureRecuperationParClient;
    private int poidsTotal;
    private int prixTotal;
    private int volumeTotal;
    private enumEtatCommande etat;
    private String idResidence;
    private List<String> casiersId;
    private String idCoursier;
    private String idClient;
    private Position positionLivraison;
    private ArrayList<MagasinsCommande> magasinsCommande;
    private String paymentId;
    private String authorizationId;
    private String mdpClient;
    private String mdpCoursier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getPoidsTotal() {
        return poidsTotal;
    }

    public void setPoidsTotal(int poidsTotal) {
        this.poidsTotal = poidsTotal;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(int volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public enumEtatCommande getEtat() {
        return etat;
    }

    public void setEtat(enumEtatCommande etat) {
        this.etat = etat;
    }

    public String getIdResidence() {
        return idResidence;
    }

    public void setIdResidence(String idResidence) {
        this.idResidence = idResidence;
    }

    public String getIdCoursier() {
        return idCoursier;
    }

    public void setIdCoursier(String idCoursier) {
        this.idCoursier = idCoursier;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
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

    public List<String> getCasiersId() {
        return casiersId;
    }

    public void setCasiersId(List<String> casiersId) {
        this.casiersId = casiersId;
    }

    public String getMdpClient() {
        return mdpClient;
    }

    public void setMdpClient(String mdpClient) {
        this.mdpClient = mdpClient;
    }

    public String getMdpCoursier() {
        return mdpCoursier;
    }

    public void setMdpCoursier(String mdpCoursier) {
        this.mdpCoursier = mdpCoursier;
    }
}
