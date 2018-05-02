package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Coursier;
import fr.insa.fmc.javaback.entity.Magasin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class CoursierRepositoryCustomImpl implements CoursierRepositoryCustom {
    @Autowired
    private MongoOperations operations;

    @Override
    public Coursier connectionQuery(String email, String mdp){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email).and("mdp").is(mdp));
        return operations.findOne(query,Coursier.class);
    }
}
