package com.ebookstore.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="password_reset_token")
public class PasswordResetToken {
	
	private static final int EXPIRATION = 60 * 24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="password_reset_token_id")
	private int passwordResettokenId;
	
	@Column(name="token")
	private String token;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="expiry_date")
	private Date expiryDate;
	
	public PasswordResetToken() {
		super();
	}

	public PasswordResetToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

	private Date calculateExpiryDate (int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}
	
	public void updateToken(String token) {
		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

	public int getPasswordResettokenId() {
		return passwordResettokenId;
	}

	public void setPasswordResettokenId(int passwordResettokenId) {
		this.passwordResettokenId = passwordResettokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
