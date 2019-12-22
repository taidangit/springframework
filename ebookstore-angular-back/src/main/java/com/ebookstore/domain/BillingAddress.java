package com.ebookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "billing_address")
public class BillingAddress {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "billing_address_id")
	private int billingAddressId;
	
	@Column(name = "billing_address_city")
	private String billingAddressCity;
	
	@Column(name = "billing_address_country")
	private String billingAddressCountry;
	
	@Column(name = "billing_address_name")
	private String billingAddressName;
	
	@Column(name = "billing_address_state")
	private String billingAddressState;
	
	@Column(name = "billing_address_street")
	private String billingAddressStreet;
	
	@Column(name = "billing_address_zipcode")
	private String billingAddressZipcode;
	
	public int getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(int billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	public String getBillingAddressCity() {
		return billingAddressCity;
	}

	public void setBillingAddressCity(String billingAddressCity) {
		this.billingAddressCity = billingAddressCity;
	}

	public String getBillingAddressCountry() {
		return billingAddressCountry;
	}

	public void setBillingAddressCountry(String billingAddressCountry) {
		this.billingAddressCountry = billingAddressCountry;
	}

	public String getBillingAddressName() {
		return billingAddressName;
	}

	public void setBillingAddressName(String billingAddressName) {
		this.billingAddressName = billingAddressName;
	}

	public String getBillingAddressState() {
		return billingAddressState;
	}

	public void setBillingAddressState(String billingAddressState) {
		this.billingAddressState = billingAddressState;
	}

	public String getBillingAddressStreet() {
		return billingAddressStreet;
	}

	public void setBillingAddressStreet(String billingAddressStreet) {
		this.billingAddressStreet = billingAddressStreet;
	}

	public String getBillingAddressZipcode() {
		return billingAddressZipcode;
	}

	public void setBillingAddressZipcode(String billingAddressZipcode) {
		this.billingAddressZipcode = billingAddressZipcode;
	}

	
}
