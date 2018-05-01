package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Coursier;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoursierRepository extends CrudRepository<Coursier,Long> {
    @Override
    Optional<Coursier> findById(Long id);
}
