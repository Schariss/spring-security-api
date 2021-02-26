package app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import app.model.AppUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;

	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			AppUser user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		User springUser = (User) authResult.getPrincipal();
		List<String> roles = new ArrayList<>();
		authResult.getAuthorities().forEach(a -> {
			roles.add(a.getAuthority());
		});
		String jwtToken = JWT.create()
				.withIssuer(request.getRequestURI())
			  .withSubject(springUser.getUsername())
			  .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
			  .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
			  .sign(Algorithm.HMAC512(SecurityConstants.SECRET));
		/* SecurityConstants.TOKEN_PREFIX + jwtToken */
		response.addHeader(SecurityConstants.HEADER_STRING, jwtToken);
	}
}
