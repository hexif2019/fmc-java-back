package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Residence;

import java.util.ArrayList;
import java.util.List;

public interface ResidenceRepositoryCustom {
    List<Residence> findResidenceByCodePostal(String codePostal);
}
