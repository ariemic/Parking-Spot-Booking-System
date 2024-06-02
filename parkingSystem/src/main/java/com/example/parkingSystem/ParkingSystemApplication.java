package com.example.parkingSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ParkingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingSystemApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner myCommandLineRunner() {
//		return args -> {
//			// Your MySQL query execution code goes here
//			// For example:
//			// myRepository.executeMySqlQuery();
//		};
//	}


}
