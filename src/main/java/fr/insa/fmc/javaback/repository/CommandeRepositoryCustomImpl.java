package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

public class CommandeRepositoryCustomImpl implements CommandeRepositoryCustom {

    @Autowired
    private MongoOperations operations;

    @Override
    public Commande updateCommande(Commande commande){
        Commande newCommande = new Commande();
        return null;
    }

}
