package com.ebookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/hours")
	public String hours() {
		return "hours";
	}
	
	@GetMapping("/faq")
	public String faq() {
		return "faq";
	}
	
}
