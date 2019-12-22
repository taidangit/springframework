package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

	private String[] data = {
			"Beware of the woflf",
			"Diligence is the mother of good luck ",
			"The journey is the reward"
	};
	
	private Random myRandom=new Random();
		
	public String getFortune() {
		int index=myRandom.nextInt(data.length);
		String theFortune=data[index];
		return theFortune;
	}

}
