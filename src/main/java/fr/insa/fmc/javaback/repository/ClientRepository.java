package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long> {

    @Override
   void deleteById(Long id);

    @Override
    Optional<Client> findById(Long id);

/*
    @Override
    void delete(Optional<Client> client);

    @Override
    Optional<Client> findById(Long id);
*/

}