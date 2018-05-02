package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Residence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;

public class ResidenceRepositoryCustomImpl implements ResidenceRepositoryCustom {

    @Autowired
    private MongoOperations operations;

    @Override
    public List<Residence> findResidenceByCodePostal(String codePostal) {
        Query query = new Query();
        query.addCriteria(Criteria.where("codePostal").is(codePostal));
        return operations.find(query,Residence.class);
    }
}
