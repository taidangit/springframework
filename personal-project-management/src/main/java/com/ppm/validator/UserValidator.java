package com.ppm.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ppm.domain.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		if(user.getUserId() != 0) {
			if(!user.getNewPassword().equals(user.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "Match", "Password must be match.");
			}
			
		} else {
			if(!user.getPassword().equals(user.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "Match", "Password must be match.");
			}
		}
		
		
		
	}

}
