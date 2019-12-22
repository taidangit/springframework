package com.luv2code.springdemo.entity;

import java.util.LinkedHashMap;

public class Student {
	private String firstName;
	private String lastName;
	private String country;
	private String favoriteLanguage;
	private String[] operatingSystems;
	//private LinkedHashMap<String, String> countryOptions;
	private LinkedHashMap<String, String> favoriteLanguageOptions;
	
	public Student() {
		super();
//		countryOptions=new LinkedHashMap<>();
//		countryOptions.put("BR", "Brazil");
//		countryOptions.put("FR", "France");
//		countryOptions.put("GE", "Germany");
//		countryOptions.put("IN", "India");
//		countryOptions.put("US", "United States of America");
		
		// populate favorite language options
		favoriteLanguageOptions = new LinkedHashMap<>();

		// parameter order: value, display label
		//
		favoriteLanguageOptions.put("Java", "Java");
		favoriteLanguageOptions.put("C#", "C#");
		favoriteLanguageOptions.put("PHP", "PHP");
		favoriteLanguageOptions.put("Ruby", "Ruby");	
	}
	
	public String[] getOperatingSystems() {
		return operatingSystems;
	}
	public void setOperatingSystems(String[] operatingSystems) {
		this.operatingSystems = operatingSystems;
	}
	
	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}
	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}
	
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
//	public LinkedHashMap<String, String> getCountryOptions() {
//		return countryOptions;
//	}

	public LinkedHashMap<String, String> getFavoriteLanguageOptions() {
		return favoriteLanguageOptions;
	}

	public void setFavoriteLanguageOptions(LinkedHashMap<String, String> favoriteLanguageOptions) {
		this.favoriteLanguageOptions = favoriteLanguageOptions;
	}

//	public void setCountryOptions(LinkedHashMap<String, String> countryOptions) {
//		this.countryOptions = countryOptions;
//	}
	
	
}
