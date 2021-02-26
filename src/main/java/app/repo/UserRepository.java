package app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import app.model.AppUser;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<AppUser, Long>{

	//@RestResource(path = "/byUsername")
	public AppUser findByUsername(String username);
}

