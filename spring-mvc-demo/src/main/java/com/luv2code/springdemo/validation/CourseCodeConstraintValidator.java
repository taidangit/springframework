package com.luv2code.springdemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

	private String[] coursePrefixes;
	
	public void initialize(CourseCode courseCode) {
		coursePrefixes=courseCode.value();
	}
	
	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext context) {
		boolean result = false;
		
		if (theCode != null) {
			for (String tempPrefix : coursePrefixes) {
				
				if (theCode.startsWith(tempPrefix)) {
					result = true;
					break;
				}
			}
		} else {
			result = false;
		}
		
		return result;
	}

}
