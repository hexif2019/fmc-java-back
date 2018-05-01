package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Magasin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MagasinRepository extends CrudRepository<Magasin,Long>,MagasinRepositoryCustom {
    @Override
    Optional<Magasin> findById(Long id);

}
