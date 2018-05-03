package fr.insa.fmc.javaback.wrapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.insa.fmc.javaback.entity.enums.enumEtatCasier;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CasierDisponibilite {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;

    private enumEtatCasier etat;


    public CasierDisponibilite(Date date) {
        this.date = date;
        etat = enumEtatCasier.LIBRE;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public enumEtatCasier getEtat() {
        return etat;
    }

    public void setEtat(enumEtatCasier etat) {
        this.etat = etat;
    }
}
