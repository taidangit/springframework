package com.dangphattai.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="bill")
public class Bill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bill_id")
	private int billId;
	
	private String total;
	
	@NotBlank(message="is required")
	@Column(name="address")
	private String address;
	
	@NotBlank(message="is required")
	@Size(min=5, message="Enter atleast 5 Characters.")	
	@Column(name="name")
	private String name;
	
	@NotBlank(message="is required")
	@Column(name="phone")
	private String phone;

	
	@NotBlank(message="is required")
	@Email(message="Email is not valid")
	@Column(name="email_address")
	private String emailAddress;
	
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="bill")
	private List<BillDetail> billDetails;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	public List<BillDetail> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(List<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


}
