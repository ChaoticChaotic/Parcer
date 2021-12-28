package com.ChaoticChaotic.parcer;

import com.ChaoticChaotic.parcer.service.ParcerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ParcerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ParcerService parcerService) {
		return args -> {
			parcerService.start();
		};

	}
}
