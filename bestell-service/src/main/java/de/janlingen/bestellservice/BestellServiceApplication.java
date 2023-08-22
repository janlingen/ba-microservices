package de.janlingen.bestellservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BestellServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestellServiceApplication.class, args);
	}

}
