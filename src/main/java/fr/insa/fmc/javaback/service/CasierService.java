package fr.insa.fmc.javaback.service;

import fr.insa.fmc.javaback.entity.Casier;
import fr.insa.fmc.javaback.entity.enums.enumEtatCasier;
import fr.insa.fmc.javaback.wrapper.CasierDisponibilite;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CasierService {

    public static List<CasierDisponibilite> SetCasierToOccupe(Date date, List<CasierDisponibilite> entry){
        boolean hasDate = false;
        for (CasierDisponibilite anEntry : entry) {
            if (anEntry.getDate().equals(date) && anEntry.getEtat() == enumEtatCasier.LIBRE) {
                anEntry.setEtat(enumEtatCasier.OCCUPE);
                hasDate = true;
            }
        }
        if(!hasDate){
            CasierDisponibilite cd = new CasierDisponibilite(date);
            entry.add(cd);
            return entry;
        }else return null;
    }

    public static List<CasierDisponibilite> SetCasierToLibre(Date date,List<CasierDisponibilite> entry){
        for(CasierDisponibilite anEntry : entry){
            if(anEntry.getDate().equals(date)) anEntry.setEtat(enumEtatCasier.LIBRE);
        }
        return entry;
    }
}
