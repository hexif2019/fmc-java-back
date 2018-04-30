package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Commande;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeRepository extends CrudRepository<Commande,Long>{
    @Override
    void deleteById(Long id);

    @Override
    Optional<Commande> findById(Long id);

}



