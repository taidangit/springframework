package com.luv2code.user;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.luv2code.validation.FieldMatch;
import com.luv2code.validation.ValidEmail;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class CrmUser {

	@NotBlank(message = "is required")
	@Size(min = 3, message = "Enter atleast 3 Characters.")
	private String userName;

	@NotBlank(message = "is required")
	@Size(min = 3, message = "Enter atleast 3 Characters.")
	private String password;
	
	@NotBlank(message = "is required")
	@Size(min = 3, message = "Enter atleast 3 Characters.")
	private String matchingPassword;

	@NotBlank(message = "is required")
	@Size(min = 3, message = "Enter atleast 3 Characters.")
	private String firstName;

	@NotBlank(message = "is required")
	@Size(min = 3, message = "Enter atleast 3 Characters.")
	private String lastName;

	@ValidEmail
	@NotBlank(message = "is required")
	@Size(min = 3, message = "Enter atleast 3 Characters.")
	private String email;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
