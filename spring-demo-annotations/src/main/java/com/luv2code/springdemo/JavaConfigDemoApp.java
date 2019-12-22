package com.luv2code.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class JavaConfigDemoApp {

	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext(SportConfig.class);
		
		Coach theCoach=context.getBean("tennisCoach", Coach.class);
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		
		((AnnotationConfigApplicationContext) context).close();
	}
}
