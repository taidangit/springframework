package com.dangphattai.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@NotBlank(message="is required")
	@Size(min=5, message="Enter atleast 5 Characters.")	
	@Column(name="fullname")
	private String fullname;
	
	@NotBlank(message="is required")
	@Size(min=5, message="Enter atleast 5 Characters.")	
	@Column(name="username")
	private String username;
	
	@NotBlank(message="is required")
	@Size(min=5, message="Enter atleast 5 Characters.")
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	@NotBlank(message="is required")
	@Email(message="Email is not valid")
	private String email;
	
	@NotBlank(message="is required")
	@Column(name="phone")
	private String phone;
	
	@OneToOne
	@JoinColumn(name="role_id")
	private Role role;

	
	public User() {
		super();
	}

	public User(String fullname, String username, String password, String email, String phone, Role role) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	
	

}
