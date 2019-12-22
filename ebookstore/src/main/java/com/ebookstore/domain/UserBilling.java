package com.ebookstore.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="user_billing")
public class UserBilling {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_billing_id")
	private int userBillingId;
	
	@NotBlank(message = "is required")
	@Column(name = "city")
	private String city;
	
	@NotBlank(message = "is required")
	@Column(name = "country")
	private String country;
	
	@NotBlank(message = "is required")
	@Column(name = "name")
	private String name;
	
	@Column(name = "state")
	private String state;
	
	@NotBlank(message = "is required")
	@Column(name = "street")
	private String street;
	
	@NotBlank(message = "is required")
	@Column(name = "zipcode")
	private String zipCode;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_payment_id")
	private UserPayment userPayment;

	public int getUserBillingId() {
		return userBillingId;
	}

	public void setUserBillingId(int userBillingId) {
		this.userBillingId = userBillingId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public UserPayment getUserPayment() {
		return userPayment;
	}

	public void setUserPayment(UserPayment userPayment) {
		this.userPayment = userPayment;
	}
	
	
}
