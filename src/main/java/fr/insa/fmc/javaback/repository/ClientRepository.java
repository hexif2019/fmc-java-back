package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Client;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client,Long> {

    public Client findById(long id);
}