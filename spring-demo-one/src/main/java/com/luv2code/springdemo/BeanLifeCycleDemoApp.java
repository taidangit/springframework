package com.luv2code.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
		
		Coach theCoach=context.getBean("myCoach", Coach.class);
		
		System.out.println(theCoach.getDailyWorkout());
		
		((ClassPathXmlApplicationContext) context).close();
	}

}
