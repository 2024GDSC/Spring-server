package com.gdsc2024.purify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PurifyApplication {
	public static void main(String[] args) {
		SpringApplication.run(PurifyApplication.class, args);
	}

}
