package com.ChaoticChaotic.parcer;

import com.ChaoticChaotic.parcer.service.ParserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParserApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ParserService parserService) {
		return args -> {
			parserService.start();
		};

	}
}
