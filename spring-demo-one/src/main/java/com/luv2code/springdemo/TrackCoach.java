package com.luv2code.springdemo;

public class TrackCoach implements Coach {

	private FortuneService fortuneService;
	
	public TrackCoach() {
		super();
		System.out.println("abc");
	}

	public TrackCoach(FortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
		System.out.println("cdf");
	}

	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	public String getDailyFortune() {
		return "Just Do It: "+ fortuneService.getFortune();
	}
	

	public void doMyStartupStuff() {
		System.out.println("TrackCoach: inside method doMyStartuoStuff");
	}
	
	public void doMyCleanupStuffYoYo() {
		System.out.println("TrackCoach: inside method doMyStartuoStuffYoYo");
	}

}
