package de.janlingen.produktservice;

import com.netflix.discovery.EurekaNamespace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ProduktServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProduktServiceApplication.class, args);
	}

}
