package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component
public class SadFortuneService implements FortuneService {

	public String getFortune() {
		return "Today is a sad day!";
	}

}
