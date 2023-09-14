package de.janlingen.bezahlservice.service;

import de.janlingen.bezahlservice.data.KontoDTO;
import de.janlingen.bezahlservice.data.KontoRepository;
import de.janlingen.bezahlservice.domain.Konto;
import de.janlingen.bezahlservice.saga.StringParser;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author janlingen
 */
@Service
@Slf4j
public class KontoService {

  private final KontoRepository kontoRepository;
  private final StringParser stringParser;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public KontoService(KontoRepository kontoRepository, StringParser stringParser,
      KafkaTemplate<String, String> kafkaTemplate) {
    this.kontoRepository = kontoRepository;
    this.stringParser = stringParser;
    this.kafkaTemplate = kafkaTemplate;
  }

  @KafkaListener(topics = "bezahl-service")
  public void checkOutbox(String kundenId){
    KontoDTO kontoDTO = KontoDTO.builder()
        .kundenId(kundenId)
        .kontostand(0.0)
        .build();
    createKonto(kontoDTO);
  }

  @KafkaListener(topics = "inventarErfolg")
  public void bestellungAbrechnen(String bestellung){
    Double gesamtwert = stringParser.parseGesamtwarenwertFromString(bestellung);
    String kundenId = stringParser.parseKundenIdFromString(bestellung);
    LocalDateTime modifiziert = stringParser.parseModifiziertFromString(bestellung);
    KontoDTO neuerKonstostand = KontoDTO.builder()
        .kundenId(kundenId)
        .kontostand(checkKontostand(kundenId) - gesamtwert)
        .build();
    if(neuerKonstostand.getKontostand() >= 0 &&
        LocalDateTime.now().minusSeconds(45).isBefore(modifiziert)){
      updateKonto(neuerKonstostand);
      kafkaTemplate.send("bezahlErfolg", bestellung);
    } else {
      kafkaTemplate.send("bezahlFehler", bestellung);
    }
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
