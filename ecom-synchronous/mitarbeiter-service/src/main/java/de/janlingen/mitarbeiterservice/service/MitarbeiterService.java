package de.janlingen.mitarbeiterservice.service;

import de.janlingen.mitarbeiterservice.data.MitarbeiterDTO;
import de.janlingen.mitarbeiterservice.data.MitarbeiterRepository;
import de.janlingen.mitarbeiterservice.domain.Mitarbeiter;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author janlingen
 */
@Service
@Slf4j
public class MitarbeiterService {

  private final MitarbeiterRepository mitarbeiterRepository;

  public MitarbeiterService(MitarbeiterRepository mitarbeiterRepository) {
    this.mitarbeiterRepository = mitarbeiterRepository;
  }

  public String createMitarbeiter(MitarbeiterDTO mitarbeiterDTO) {
    Mitarbeiter mitarbeiter = Mitarbeiter.builder()
        .username(mitarbeiterDTO.getUsername())
        .passwort(mitarbeiterDTO.getPasswort())
        .mitarbeiterId(UUID.randomUUID().toString())
        .erstellt(LocalDateTime.now())
        .modifiziert(LocalDateTime.now())
        .build();
    mitarbeiterRepository.save(mitarbeiter);
    log.info(
        "Mitarbeiter " + mitarbeiter.getMitarbeiterId() + " erstellt um " + mitarbeiter.getErstellt()
            + "!");
    return mitarbeiter.getMitarbeiterId();
  }

  public void updateMitarbeiter(MitarbeiterDTO mitarbeiterDTO) {
    Mitarbeiter mitarbeiter = mitarbeiterRepository.findByMitarbeiterId(mitarbeiterDTO.getMitarbeiterId())
        .orElse(null);
    if (mitarbeiter != null) {
      mitarbeiter.setUsername(mitarbeiterDTO.getUsername());
      mitarbeiter.setPasswort(mitarbeiterDTO.getPasswort());
      mitarbeiter.setMitarbeiterId(mitarbeiterDTO.getMitarbeiterId());
      mitarbeiter.setModifiziert(LocalDateTime.now());
      mitarbeiterRepository.save(mitarbeiter);
      log.info("Mitarbeiter " + mitarbeiter.getMitarbeiterId() + " modifiziert um "
          + mitarbeiter.getModifiziert() + "!");
    }
  }

  public String login(String username, String passwort) {
    Mitarbeiter mitarbeiter = mitarbeiterRepository.findByUsername(username).orElse(null);
    if (mitarbeiter != null) {
      System.out.println("test hier mitarbeiter");
      if (mitarbeiter.getPasswort().equals(passwort)) {
        return mitarbeiter.getMitarbeiterId();
      }
    }
    return null;
  }
}
