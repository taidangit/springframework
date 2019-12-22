package com.ebookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shipping_address")
public class ShippingAddress {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "shipping_address_id")
	private int shippingAddressId;
	
	@Column(name = "shipping_address_city")
	private String shippingAddressCity;
	
	@Column(name = "shipping_address_country")
	private String shippingAddressCountry;
	
	@Column(name = "shipping_address_name")
	private String shippingAddressName;
	
	@Column(name = "shipping_address_state")
	private String shippingAddressState;
	
	@Column(name = "shipping_address_street")
	private String shippingAddressStreet;
	
	@Column(name = "shipping_address_zipcode")
	private String shippingAddressZipcode;
	
	public int getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(int shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public String getShippingAddressCity() {
		return shippingAddressCity;
	}

	public void setShippingAddressCity(String shippingAddressCity) {
		this.shippingAddressCity = shippingAddressCity;
	}

	public String getShippingAddressCountry() {
		return shippingAddressCountry;
	}

	public void setShippingAddressCountry(String shippingAddressCountry) {
		this.shippingAddressCountry = shippingAddressCountry;
	}

	public String getShippingAddressName() {
		return shippingAddressName;
	}

	public void setShippingAddressName(String shippingAddressName) {
		this.shippingAddressName = shippingAddressName;
	}

	public String getShippingAddressState() {
		return shippingAddressState;
	}

	public void setShippingAddressState(String shippingAddressState) {
		this.shippingAddressState = shippingAddressState;
	}

	public String getShippingAddressStreet() {
		return shippingAddressStreet;
	}

	public void setShippingAddressStreet(String shippingAddressStreet) {
		this.shippingAddressStreet = shippingAddressStreet;
	}

	public String getShippingAddressZipcode() {
		return shippingAddressZipcode;
	}

	public void setShippingAddressZipcode(String shippingAddressZipcode) {
		this.shippingAddressZipcode = shippingAddressZipcode;
	}
	
}
