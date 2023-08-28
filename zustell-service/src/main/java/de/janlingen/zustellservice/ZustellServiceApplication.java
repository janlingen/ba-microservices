package de.janlingen.zustellservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ZustellServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZustellServiceApplication.class, args);
	}

}
