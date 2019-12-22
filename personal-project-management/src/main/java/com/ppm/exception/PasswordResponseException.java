package com.ppm.exception;

public class PasswordResponseException {

	private String inccorectPassword;

	public PasswordResponseException(String inccorectPassword) {
		super();
		this.inccorectPassword = inccorectPassword;
	}

	public String getInccorectPassword() {
		return inccorectPassword;
	}

	public void setInccorectPassword(String inccorectPassword) {
		this.inccorectPassword = inccorectPassword;
	}
	
	
}
