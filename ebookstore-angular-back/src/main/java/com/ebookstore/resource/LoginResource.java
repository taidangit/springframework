package com.ebookstore.resource;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebookstore.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LoginResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/token")
	public Map<String, String> token(HttpSession session, HttpServletRequest request) {
		System.out.println(request.getRemoteHost());
		
		String removeHost = request.getRemoteHost();
		int portNumber = request.getRemotePort();
		
		System.out.println(removeHost+":"+portNumber);
		System.out.println(request.getRemoteAddr());
		
		return Collections.singletonMap("token", session.getId());
	}
	
	@GetMapping("/checkSession")
	public ResponseEntity<String> checkSession() {
		return new ResponseEntity<String>("Session Active!", HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout() {
		//SecurityContextHolder.clearContext();
		return new ResponseEntity<String>("Logout Successfully!", HttpStatus.OK);
	}
}
