package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Coursier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CoursierRepository extends CrudRepository<Coursier,String>,CoursierRepositoryCustom {
    @Override
    Optional<Coursier> findById(String id);

    List<Coursier> findByResidenceId(String residenceId);
}
