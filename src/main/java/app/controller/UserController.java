package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.model.AppUser;
import app.repo.UserRepository;
import app.services.AccountService;

@RestController
public class UserController {
	
	@Autowired
	AccountService accountService;
	@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
	public AppUser signUp(@RequestBody UserForm userForm) {
		String username = userForm.getUsername();
		String password = userForm.getPassword();
		String repassword = userForm.getRepassword();
		return accountService.saveUser(username, password, repassword);
	}

	
	
}
