package com.luv2code.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {
	
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Coach theCoach=context.getBean("tennisCoach", Coach.class);
		Coach alphaCoach=context.getBean("tennisCoach", Coach.class);
		boolean result=(theCoach==alphaCoach);
		
		System.out.println("\nPointing to the same object: "+result);
		System.out.println("\nMemory location for the coach: "+theCoach);
		System.out.println("\nMemory location for the alphaCoach: "+alphaCoach);
		
		((ClassPathXmlApplicationContext) context).close();
	}
}
