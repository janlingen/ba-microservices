package de.janlingen.bezahlservice.service;

import de.janlingen.bezahlservice.data.KontoDTO;
import de.janlingen.bezahlservice.data.KontoRepository;
import de.janlingen.bezahlservice.domain.Konto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author janlingen
 */
@Service
@Slf4j
public class KontoService {

  private final KontoRepository kontoRepository;

  public KontoService(KontoRepository kontoRepository) {
    this.kontoRepository = kontoRepository;
  }

  public void createKonto(KontoDTO kontoDTO) {
    Konto konto = Konto.builder()
        .kundenId(kontoDTO.getKundenId())
        .kontostand(0.0)
        .erstellt(LocalDateTime.now())
        .modifiziert(LocalDateTime.now())
        .build();
    kontoRepository.save(konto);
    log.info(
        "Konto von " + konto.getKundenId() + " erstellt um " + konto.getErstellt()
            + "!");
  }

  public void updateKonto(KontoDTO kontoDTO) {
    Konto konto = kontoRepository.findByKundenId(kontoDTO.getKundenId())
        .orElse(null);
    if (konto != null & kontoDTO.getKontostand() >= 0) {
      konto.setKundenId(kontoDTO.getKundenId());
      konto.setKontostand(kontoDTO.getKontostand());
      konto.setModifiziert(LocalDateTime.now());
      kontoRepository.save(konto);
      log.info("Konto von " + konto.getKundenId() + " modifiziert um "
          + konto.getModifiziert() + "!");
    }
  }

  public Double checkKontostand(String kundenId) {
    Konto konto = kontoRepository.findByKundenId(kundenId).orElse(null);
    if (konto != null) {
      return konto.getKontostand();
    }
    return null;
  }
}
