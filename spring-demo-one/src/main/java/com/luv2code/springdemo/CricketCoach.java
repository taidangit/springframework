package com.luv2code.springdemo;

public class CricketCoach implements Coach {

	private FortuneService fortuneService;

	private String email;
	private String team;
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	public String getDailyWorkout() {
		return "Practice fast bowling for 15 minutes";
	}

	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	@Override
	public String toString() {
		return "CricketCoach [email=" + email + ", team=" + team + "]";
	}
	
	

}
