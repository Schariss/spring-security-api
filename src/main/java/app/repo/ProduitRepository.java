package app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.model.Produit;

@RepositoryRestResource
public interface ProduitRepository extends JpaRepository<Produit,Long> {

}
