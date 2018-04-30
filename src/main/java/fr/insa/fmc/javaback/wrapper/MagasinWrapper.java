package fr.insa.fmc.javaback.wrapper;

import fr.insa.fmc.javaback.entity.Position;

import java.util.ArrayList;

public class MagasinWrapper {
    private Long id;
    private String adresse;
    private String description;
    private String denomination;
    private String email;
    private ArrayList<ProduitWrapper> produitsWrapper;
    private String ville;
    private String codePostal;
    private String img;
    private Position position;

}
