package app.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.model.AppUser;
import app.model.AppRole;
import app.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository repo;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appuser = repo.findByUsername(username) ;
		
		if(appuser == null)
			throw new UsernameNotFoundException("User not found");
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
	    for (AppRole role : appuser.getRoles()){
	        authorities.add(new SimpleGrantedAuthority(role.getRole()));
	    }
		
		return new User(appuser.getUsername(), appuser.getPassword(), authorities);
	}
	 

}
