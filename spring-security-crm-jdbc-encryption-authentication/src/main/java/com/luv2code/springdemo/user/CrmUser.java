package com.luv2code.springdemo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CrmUser {

	@NotNull(message="is required")
	@Size(min=3, message="Enter atleast 3 Characters.")	
	private String username;
	
	@NotNull(message="is required")
	@Size(min=3, message="Enter atleast 3 Characters.")
	private String password;

	public CrmUser() {
		super();
	}

	public CrmUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


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
