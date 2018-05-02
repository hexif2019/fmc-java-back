package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Residence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidenceRepository extends CrudRepository<Residence,String>, ClientRepositoryCustom {

    @Override
    void deleteById(String id);

    @Override
    Optional<Residence> findById(String id);


    //@Query("select c from Client c where c.email = :email")
    // Client findByEmail(@Param("email") String email);

    //Client findByEmail(String email, String mdp);


/*
    @Override
    void delete(Optional<Client> client);

    @Override
    Optional<Client> findById(Long id);
*/

}
