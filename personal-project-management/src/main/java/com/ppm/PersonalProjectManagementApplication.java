package com.ppm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PersonalProjectManagementApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(PersonalProjectManagementApplication.class, args);
	}

}
