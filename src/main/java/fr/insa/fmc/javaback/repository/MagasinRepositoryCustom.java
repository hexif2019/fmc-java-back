package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Magasin;

public interface MagasinRepositoryCustom {
    Magasin connectionQuery(String email, String mdp);
}
