package com.luv2code.user;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class CrmUser {

	@NotBlank(message="is required")
	@Size(min=3, message="Enter atleast 3 Characters.")	
	private String username;
	
	@NotBlank(message="is required")
	@Size(min=3, message="Enter atleast 3 Characters.")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
