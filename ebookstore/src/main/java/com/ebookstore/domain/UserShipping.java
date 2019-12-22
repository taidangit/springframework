package com.ebookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="user_shipping")
public class UserShipping {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_shipping_id")
	private int userShippingId;
	
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
	@Column(name = "zip_code")
	private String zipCode;
	
	@Column(name = "shipping_default")
	private boolean shippingDefault;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public int getUserShippingId() {
		return userShippingId;
	}

	public void setUserShippingId(int userShippingId) {
		this.userShippingId = userShippingId;
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

	public boolean isShippingDefault() {
		return shippingDefault;
	}

	public void setShippingDefault(boolean shippingDefault) {
		this.shippingDefault = shippingDefault;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
