package de.janlingen.bezahlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableJpaAuditing
@EnableKafka
public class BezahlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BezahlServiceApplication.class, args);
	}
}
