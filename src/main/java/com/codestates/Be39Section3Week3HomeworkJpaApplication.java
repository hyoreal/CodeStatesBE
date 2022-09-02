package com.codestates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Be39Section3Week3HomeworkJpaApplication {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "mapping");
		SpringApplication.run(Be39Section3Week3HomeworkJpaApplication.class, args);
	}

}
