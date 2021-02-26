package app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.model.AppRole;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<AppRole, Integer>{
	public AppRole findByRole(String role);
}
