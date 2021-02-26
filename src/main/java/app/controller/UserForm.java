package app.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

	private String username;
	private String password;
	private String repassword;
	
}
