package com.luv2code.springdemo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springdemo.entity.Student;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Value("#{countryOptions}") 
    private Map<String, String> countryOptions;
	
	@GetMapping("/showForm")
	public String showForm(Model theModel) {

		theModel.addAttribute("student", new Student());
		
		// add the country options to the model 
	    theModel.addAttribute("theCountryOptions", countryOptions);
	    
	    return "student-form";
		
	}
	
	@PostMapping("/processForm")
	public String processForm(@ModelAttribute("student") Student theStudent, Model theModel) {
		theModel.addAttribute("student", theStudent);
		return "student-confirmation";
	}
	
}
