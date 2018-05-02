package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Coursier;

public interface CoursierRepositoryCustom {
    Coursier connectionQuery(String email, String mdp);
}
