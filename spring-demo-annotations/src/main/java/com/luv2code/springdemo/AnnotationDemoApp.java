package com.luv2code.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		/*Coach theCoach=context.getBean("thatSillyCoach", Coach.class);*/
		Coach theCoach=context.getBean("tennisCoach", Coach.class);
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		
		((ClassPathXmlApplicationContext) context).close();

	}

}
