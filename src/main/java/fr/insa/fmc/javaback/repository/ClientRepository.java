package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long>, ClientRepositoryCustom {

    @Override
   void deleteById(Long id);

    @Override
    Optional<Client> findById(Long id);


    //@Query("select c from Client c where c.email = :email")
   // Client findByEmail(@Param("email") String email);

    //Client findByEmail(String email, String mdp);


/*
    @Override
    void delete(Optional<Client> client);
*/


}