package com.ebookstore.utility;

import java.util.List;
import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ebookstore.domain.CartItem;
import com.ebookstore.domain.User;
import com.ebookstore.domain.UserOrder;


@Component
public class MailConstructor {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public SimpleMailMessage constructNewUserEmail(User user, String password) {
		
		String message = "\nPlease use the following credentials to log in and edit your personal information including your own password."
				+ "\nUsername:"+user.getUsername()+"\nPassword:"+password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("My Bookstore - New User");
		email.setText(message);
		email.setFrom(env.getProperty("support.email"));
		return email;
		
	}
	
	public MimeMessagePreparator constructOrderConfirmationEmail (User user, UserOrder order, Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItems", order.getCartItems());
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Order Confirmation - "+order.getUserOrderId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("dangphattai92@gmail.com"));
			}
		};
		
		return messagePreparator;
	}
}
