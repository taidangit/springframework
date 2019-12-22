package com.ebookstore.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="user_payment")
public class UserPayment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_payment_id")
	private int userPaymentId;
	
	@Column(name="type")
	private String type;
	
	@NotBlank(message = "is required")
	@Column(name="card_name")
	private String cardName;
	
	@NotBlank(message = "is required")
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expiry_month")
	private int expiryMonth;
	
	@Column(name="expiry_year")
	private int expiryYear;
	
	@Column(name="cvc")
	private int cvc;
	
	@NotBlank(message = "is required")
	@Column(name="holder_name")
	private String holderName;
	
	@Column(name="default_payment")
	private boolean defaultPayment;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne(mappedBy = "userPayment", cascade= CascadeType.ALL)
	private UserBilling userBilling;

	public int getUserPaymentId() {
		return userPaymentId;
	}

	public void setUserPaymentId(int userPaymentId) {
		this.userPaymentId = userPaymentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public boolean isDefaultPayment() {
		return defaultPayment;
	}

	public void setDefaultPayment(boolean defaultPayment) {
		this.defaultPayment = defaultPayment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserBilling getUserBilling() {
		return userBilling;
	}

	public void setUserBilling(UserBilling userBilling) {
		this.userBilling = userBilling;
	}
	
	
}
