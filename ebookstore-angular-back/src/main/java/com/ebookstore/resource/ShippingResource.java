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
import com.ebookstore.domain.UserShipping;
import com.ebookstore.service.UserService;
import com.ebookstore.service.UserShippingService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/shipping")
public class ShippingResource {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private UserShippingService userShippingService;
	
	
	@GetMapping("/list/{username}")
	public ResponseEntity<List<UserShipping>> listOfUserShipping(@PathVariable String username) {
		User user = userService.findByUsername(username);
		List<UserShipping> userShippings = user.getUserShippings();
		
		return new ResponseEntity<List<UserShipping>>(userShippings, HttpStatus.OK);
		
	}
	
	@PostMapping("/add/{username}")
	public ResponseEntity<String> addNewUserShipping(
			@PathVariable String username,
			@RequestBody UserShipping userShipping
			) {
		
		User user = userService.findByUsername(username);
		
		userShipping.setShippingDefault(true);
		userShipping.setUser(user);
		
		userShippingService.save(userShipping);
		
		return new ResponseEntity<String>("Added successfully!", HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteUserShipping(@PathVariable("id") int userShippingId) {
		userShippingService.deleteById(userShippingId);
		
		return new ResponseEntity<String>("Removed Successfully!", HttpStatus.OK);
		
	}
	
	@GetMapping("/setDefaultShipping/{id}/{username}")
	public ResponseEntity<String> setDefaultShipping(@PathVariable("id") int userShippingtId, @PathVariable String username) {
		User user = userService.findByUsername(username);
		userShippingService.setUserDefaultShipping(userShippingtId, user);
		
		return new ResponseEntity<String>("Updated Successfully!", HttpStatus.OK);
	}
	
}
