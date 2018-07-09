package com.game.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.game" })
public class BolApplication {

	public static void main(String[] args) {
		SpringApplication.run(BolApplication.class, args);
	}
}
