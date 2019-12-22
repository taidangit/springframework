package com.luv2code.springdemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	
	@GetMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	@PostMapping("/processForm")
	public String letsShouDude(@RequestParam("studentName") String theName, Model model) {
	
		String result="Yo! "+theName.toUpperCase();
		model.addAttribute("message", result);
		
		return "helloworld";
	}
	
}
