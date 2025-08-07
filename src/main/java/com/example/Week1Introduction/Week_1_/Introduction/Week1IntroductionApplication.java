package com.example.Week1Introduction.Week_1_.Introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week1IntroductionApplication implements CommandLineRunner
{
	@Autowired
	Apple obj;

	public static void main(String[] args) {
		SpringApplication.run(Week1IntroductionApplication.class, args);


	}


	@Override
	public void run(String... args) throws Exception {

	}
}
