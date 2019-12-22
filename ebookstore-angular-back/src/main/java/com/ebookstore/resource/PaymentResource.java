package com.ebookstore.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebookstore.domain.User;
import com.ebookstore.domain.UserBilling;
import com.ebookstore.domain.UserPayment;
import com.ebookstore.service.UserPaymentService;
import com.ebookstore.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/payment")
public class PaymentResource {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private UserPaymentService userPaymentService;
	
	
	@GetMapping("/list/{username}")
	public ResponseEntity<List<UserPayment>> listOfCreditCards(@PathVariable String username) {
		User user = userService.findByUsername(username);
		List<UserPayment> userPayments = user.getUserPayments();
		
		return new ResponseEntity<List<UserPayment>>(userPayments, HttpStatus.OK);
	}
	
	@PostMapping("/add/{username}")
	public ResponseEntity<String> addNewCreditCard(
			@PathVariable String username,
			@RequestBody UserPayment userPayment
			) {
		
		User user = userService.findByUsername(username);
		
		UserBilling userBilling = userPayment.getUserBilling();
		
		userPayment.setDefaultPayment(true);
		userPayment.setUser(user);
		userBilling.setUserPayment(userPayment);
		
		userPaymentService.save(userPayment);
		
		return new ResponseEntity<String>("Added successfully!", HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteCreditCard(@PathVariable("id") int userPaymentId) {
		userPaymentService.deleteById(userPaymentId);
		
		return new ResponseEntity<String>("Removed Successfully!", HttpStatus.OK);
		
	}
	
	@GetMapping("/setDefaultPayment/{id}/{username}")
	public ResponseEntity<String> setDefaultPayment(@PathVariable("id") int userPaymentId, @PathVariable String username) {
		User user = userService.findByUsername(username);
		userPaymentService.setUserDefaultPayment(userPaymentId, user);
		
		return new ResponseEntity<String>("Updated Successfully!", HttpStatus.OK);
	}
	

}
