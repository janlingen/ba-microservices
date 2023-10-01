package de.janlingen.bestellservice.service;

import de.janlingen.bestellservice.data.BestellungDTO;
import de.janlingen.bestellservice.data.BestellungRepository;
import de.janlingen.bestellservice.data.ProduktDTO;
import de.janlingen.bestellservice.domain.Bestellung;
import de.janlingen.bestellservice.domain.Produkt;
import de.janlingen.bestellservice.saga.StringParser;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author janlingen
 */
@Service
@Slf4j
public class BestellungService {

  private final BestellungRepository bestellungRepository;
  private final StringParser stringParser;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public BestellungService(BestellungRepository bestellungRepository,
      StringParser stringParser, KafkaTemplate<String, String> kafkaTemplate) {
    this.bestellungRepository = bestellungRepository;
    this.stringParser = stringParser;
    this.kafkaTemplate = kafkaTemplate;
  }

  @KafkaListener(topics = "inventarFehler")
  public void inventarFehler(String bestellung){
    System.out.println("Inventar Fehler bei Bestellung: " + bestellung);
  }

  @KafkaListener(topics = "bezahlFehler")
  public void bezahlFehler(String bestellung){
    kafkaTemplate.send("inventarKorrigieren", bestellung);
  }

  @KafkaListener(topics = "bezahlErfolg")
  public void bezahlErfolg(String bestellung){
    Bestellung finishBestellung = stringParser.parseBestellungFromString(bestellung);
    finishBestellung.setBestellungExcecuted(true);
    bestellungRepository.save(finishBestellung);
    createBestellung(finishBestellung.getKundenId());
  }

  public void finishBestellung(Long bestellungId, String kundenId) {
    Bestellung bestellung = bestellungRepository.findById(bestellungId).orElse(null);
    if (bestellung != null && bestellung.getKundenId().equals(kundenId)) {
      bestellung.setModifiziert(LocalDateTime.now());
      kafkaTemplate.send("bestellungPlaziert", bestellung.toString());
    }
  }


  public BestellungDTO createBestellung(String kundenId) {
    List<Bestellung> bestellungen = bestellungRepository.findAllByKundenId(kundenId).stream()
        .filter(b -> b.getBestellungExcecuted().equals(false)).toList();
    if (bestellungen.isEmpty()) {
      Bestellung bestellung = Bestellung.builder()
          .kundenId(kundenId)
          .produktList(Collections.emptyList())
          .bestellungExcecuted(false)
          .erstellt(LocalDateTime.now())
          .modifiziert(LocalDateTime.now())
          .build();
      bestellung = bestellungRepository.save(bestellung);
      return toBestellungDTO(bestellung);
    }
    return BestellungDTO.builder()
        .id(bestellungen.get(0).getId())
        .kundenId(bestellungen.get(0).getKundenId())
        .produktList(bestellungen.get(0).getProduktList().stream().map(BestellungService::toProduktDTO).toList())
        .bestellungExcecuted(bestellungen.get(0).getBestellungExcecuted())
        .build();
  }

  public BestellungDTO updateBestellung(ProduktDTO produktDTO, String kundenId, Long bestellungId) {
    Bestellung bestellung = bestellungRepository.findById(bestellungId).orElse(null);
    if (bestellung != null && bestellung.getKundenId().equals(kundenId)) {
      List<Produkt> produkte = bestellung.getProduktList().stream()
          .filter(produkt -> produkt.getName().equals(produktDTO.getName())).toList();
      if (!produkte.isEmpty()) {
        produkte.get(0).setAnzahl(produktDTO.getAnzahl());
      } else {
        bestellung.getProduktList().add(
            Produkt.builder().name(produktDTO.getName()).anzahl(produktDTO.getAnzahl())
                .preis(produktDTO.getPreis()).build());
      }
      bestellung.setModifiziert(LocalDateTime.now());
      return toBestellungDTO(bestellungRepository.save(bestellung));
    }
    return null;
  }

  public Double getBestellungskosten(Long bestellungId) {
    Bestellung bestellung = bestellungRepository.findById(bestellungId).orElse(null);
    if (bestellung != null) {
      return bestellung.getProduktList().stream()
          .mapToDouble(Produkt::getGesamtwert)
          .reduce(0.0, Double::sum);
    }
    return 0.0;
  }

  public BestellungDTO getBestellung(Long bestellungId) {
    Bestellung bestellung = bestellungRepository.findById(bestellungId).orElse(null);
    if (bestellung != null) {
      return toBestellungDTO(bestellung);
    }
    return null;
  }

  private BestellungDTO toBestellungDTO(Bestellung bestellung) {
    return BestellungDTO.builder()
        .id(bestellung.getId())
        .kundenId(bestellung.getKundenId())
        .produktList(bestellung.getProduktList().stream().map(BestellungService::toProduktDTO).toList())
        .bestellungExcecuted(bestellung.getBestellungExcecuted())
        .build();
  }

  private static ProduktDTO toProduktDTO(Produkt produkt){
    return ProduktDTO.builder().name(produkt.getName()).anzahl(produkt.getAnzahl()).preis(
        produkt.getPreis()).build();
  }


}
