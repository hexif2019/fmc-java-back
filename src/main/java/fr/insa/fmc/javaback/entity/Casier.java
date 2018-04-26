package fr.insa.fmc.javaback.entity;

import fr.insa.fmc.javaback.model.CasierDisponibilite;
import org.springframework.data.annotation.Id;

import java.util.Queue;

public class Casier {
    @Id
    private Long id;
    private int volume;
    private int longueur;
    private int largeur;
    private int hauteur;
    private Queue<CasierDisponibilite> disponibilites;

    public Long getId() {
        return id;
    }

    public int getVolume() {
        return volume;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public Queue<CasierDisponibilite> getDisponibilites() {
        return disponibilites;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setDisponibilites(Queue<CasierDisponibilite> disponibilites) {
        this.disponibilites = disponibilites;
    }
}
