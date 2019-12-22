package com.luv2code.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="messages")
public class Messages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8164450786808454610L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	private int messageId;
	
	@NotBlank(message = "is required")
	@Column(name="subject")
	private String subject;
	
	@NotBlank(message = "is required")
	@Column(name="content")
	private String content;
	
	@NotBlank(message = "is required")
	@Column(name="name")
	private String name;
	
	@NotBlank(message = "is required")
	@Email(message = "Invalid email")
	@Column(name="email")
	private String email;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
