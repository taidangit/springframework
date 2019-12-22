package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SwimCoach implements Coach {

	@Autowired
	@Qualifier("sadFortuneService")
	private FortuneService fortuneService;
	
	@Value("${email}")
	private String email;
	
	@Value("${team}")
	private String team;

	public String getDailyWorkout() {
		return "Swim 1000 meters as awarm up.";
	}

	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	@Override
	public String toString() {
		return "SwimCoach [email=" + email + ", team=" + team + "]";
	}
	

}
