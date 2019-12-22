package com.luv2code.springdemo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*@Component("thatSillyCoach")*/
@Component
@Scope("prototype")
public class TennisCoach implements Coach {

	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	
//	@Autowired
//	public TennisCoach(@Qualifier("randomFortuneService") FortuneService fortuneService) {
//		super();
//		this.fortuneService = fortuneService;
//	}
	
	/*@Autowired
	public void setFortuneService(@Qualifier("randomFortuneService") FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}*/
	
	public TennisCoach() {
		super();
		System.out.println(">> TennisCoach: inside default constructor");
	}
	
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}


	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	
	@PostConstruct
	public void doMyStartupStuff() {
		System.out.println(">> Tennis Coach: inside of doMyStartupStuff()");
	}
	
	@PreDestroy
	public void doMyCleanupStuff() {
		System.out.println(">> Tennis Coach: inside of doMyCleanupStuff()");
	}
	
	

}
