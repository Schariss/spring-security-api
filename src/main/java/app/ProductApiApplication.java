package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import app.services.AccountService;

import app.model.AppRole;

import app.model.Produit;
import app.repo.ProduitRepository;

@SpringBootApplication
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*")
public class ProductApiApplication implements CommandLineRunner{

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	AccountService accountService;
	
	public static void main(String[] args) {
		SpringApplication.run(ProductApiApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
    }

	@Override
	public void run(String... args) throws Exception {
		Produit p1 = new Produit(null, "Samsung TV", 30, 3);
		Produit p2 = new Produit(null, "Mac Pro", 180, 2);
		Produit p3 = new Produit(null, "Téléphone", 10, 2);
		Produit p4 = new Produit(null, "Projecteur", 60, 7);
				
		AppRole r1 = new AppRole(null, "USER");
		AppRole r2 = new AppRole(null, "ADMIN");
		
		accountService.saveRole(r1);
		accountService.saveRole(r2);
		
		accountService.saveUser("admin", "mdp1", "mdp1");
		
		accountService.addRoleToUser("admin", "ADMIN");
		
		produitRepository.save(p1);
		produitRepository.save(p2);
		produitRepository.save(p3);
		produitRepository.save(p4);
		
	}

}
