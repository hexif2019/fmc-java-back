package fr.insa.fmc.javaback.repository;

import fr.insa.fmc.javaback.entity.Produit;
import org.springframework.data.repository.CrudRepository;

public interface ProduitRepository  extends CrudRepository<Produit,String>{
}
