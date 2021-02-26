package app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.model.AppRole;
import app.model.AppUser;
import app.repo.RoleRepository;
import app.repo.UserRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public AppUser saveUser(String username, String password, String repassword) {
		AppUser user = userRepository.findByUsername(username);
		if(user != null) throw new RuntimeException("Username existant");
		if(!password.equals(repassword))
			throw new RuntimeException("Mots de passe différents");
		AppUser user2 = new AppUser();
		user2.setUsername(username);
		user2.setPassword(bCryptPasswordEncoder.encode(password));
		userRepository.save(user2);
		accountService.addRoleToUser(username, "USER");
		return user2;
		
	}
	
	public AppUser findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public AppRole saveRole(AppRole r) {
		return roleRepository.save(r);
	}
	
	public void addRoleToUser(String username, String roleName) {
		AppUser user = userRepository.findByUsername(username);
		AppRole role = roleRepository.findByRole(roleName);
		user.getRoles().add(role);
	}
	
}
