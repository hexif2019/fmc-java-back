package fr.insa.fmc.javaback.service;

import fr.insa.fmc.javaback.entity.Coursier;

import java.util.List;

public class CoursierService {

    public static int attributeCommandeToCoursier(List<Coursier> coursiers){
        int index = 0;
        int max = Integer.MAX_VALUE;
        for(int i=0;i<coursiers.size();i++){
            if(coursiers.get(i).getCommandesEnCours().size()<max) index = i;
        }
        return index;
    }
}
