package de.janlingen.mitarbeiterservice;

import de.janlingen.mitarbeiterservice.data.MitarbeiterDTO;
import de.janlingen.mitarbeiterservice.service.MitarbeiterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MitarbeiterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MitarbeiterServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner createInitialMitarbeiter(MitarbeiterService mitarbeiterService) {
		return args -> {
			MitarbeiterDTO mitarbeiterDTO = new MitarbeiterDTO();
			mitarbeiterDTO.setUsername("testMit");
			mitarbeiterDTO.setPasswort("test");
			String mitarbeiterId = mitarbeiterService.createMitarbeiter(mitarbeiterDTO);
		};
	}
}
