package fr.insa.fmc.javaback.repository;
import fr.insa.fmc.javaback.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.parser.Entity;

public class ClientRepositoryCustomImpl implements ClientRepositoryCustom {

    @Autowired
    private MongoOperations operations;


    @Override
    public Client connectionQuery(String email, String mdp){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email).and("mdp").is(mdp));
        //query.addCriteria(Criteria.where("Client.email").is(email));
        return operations.findOne(query,Client.class);
        //Criteria c = Criteria.where("email").is(email).and("mdp").is(mdp);
        //return operations.find(Query.query(c),Client.class);

    }

//    @Override
//    @Query("select c from Client c where c.email=:email")
//    public Client connectionQuery(String email, String mdp){

//    }

}
