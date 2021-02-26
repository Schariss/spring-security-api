package app.services;

import app.model.AppRole;
import app.model.AppUser;

public interface AccountService {

	public AppUser saveUser(String username, String password, String repassword);
	public AppRole saveRole(AppRole r);
	public AppUser findUserByUsername(String username);
	public void addRoleToUser(String username, String role);
}
