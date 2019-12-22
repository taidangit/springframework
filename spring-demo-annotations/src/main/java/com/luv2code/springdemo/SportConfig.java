package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages="com.luv2code.springdemo")
@PropertySource(value= {"classpath:properties/sport.properties"})
public class SportConfig {

//	@Bean
//	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}
	
	/*@Bean
	public FortuneService sadFortuneService() {
		return new SabFortuneService();
	}
	
	@Bean
	public Coach swimCoach() {
		return new SwimCoach(sadFortuneService());
	}
*/
}
