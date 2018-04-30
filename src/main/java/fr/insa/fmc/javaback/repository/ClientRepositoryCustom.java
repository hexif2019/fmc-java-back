package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Client;

public interface ClientRepositoryCustom {
    Client connectionQuery(String email, String mdp);
}
