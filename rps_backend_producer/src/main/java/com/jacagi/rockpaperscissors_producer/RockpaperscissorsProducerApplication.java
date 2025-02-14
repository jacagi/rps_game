package com.jacagi.rockpaperscissors_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "com.jacagi.rockpaperscissors_producer.**"})
@EnableScheduling
public class RockpaperscissorsProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RockpaperscissorsProducerApplication.class, args);
	}

}
