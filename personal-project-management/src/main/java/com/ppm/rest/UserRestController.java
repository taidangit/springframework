package com.ppm.rest;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppm.domain.User;
import com.ppm.exception.PasswordException;
import com.ppm.payload.JWTLoginSuccessReponse;
import com.ppm.payload.LoginRequest;
import com.ppm.security.JwtTokenProvider;
import com.ppm.security.SecurityConstants;
import com.ppm.service.MapValidationService;
import com.ppm.service.UserService;
import com.ppm.validator.UserValidator;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MapValidationService mapValidationService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
		userValidator.validate(user, result);
		
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) {
			return errorMap;
		}
		
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) {
			return errorMap;
		}
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = SecurityConstants.TOKEN_PREFIX+jwtTokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JWTLoginSuccessReponse(true, jwt));
	}
	
	@GetMapping("")
	public ResponseEntity<?> getUser(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping("/updateUserInfo")
	public ResponseEntity<?> updateUserInfo(@Valid @RequestBody User user, BindingResult result) {
		userValidator.validate(user, result);
		
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) {
			return errorMap;
		}
		
		userService.update(user);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
