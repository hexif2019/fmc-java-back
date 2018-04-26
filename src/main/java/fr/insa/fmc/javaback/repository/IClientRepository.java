package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Client;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IClientRepository extends CrudRepository<Client,Long> {

    Optional<Client> findById(String s);

    @Override
    void delete(Client entity);
}