package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Commande;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeRepository extends CrudRepository<Commande,String>{
    @Override
    void deleteById(String id);

    @Override
    Optional<Commande> findById(String id);

    @Override
    <S extends Commande> S save(S entity);
}



