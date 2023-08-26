package de.janlingen.produktservice.service;

import de.janlingen.produktservice.data.ProduktDTO;
import de.janlingen.produktservice.data.ProduktRepository;
import de.janlingen.produktservice.domain.Produkt;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author janlingen
 */
@Service
@Slf4j
public class ProduktService {

  private final ProduktRepository produktRepository;

  public ProduktService(ProduktRepository produktRepository) {
    this.produktRepository = produktRepository;
  }

  public void createProdukt(ProduktDTO produktDTO) {
    Produkt produkt = Produkt.builder()
        .name(produktDTO.getName())
        .marke(produktDTO.getMarke())
        .gewicht(produktDTO.getGewicht())
        .beschreibung(produktDTO.getBeschreibung())
        .preis(produktDTO.getPreis())
        .bildUrl(produktDTO.getBildUrl())
        .build();

    produktRepository.save(produkt);
    log.info("Produkt " + produkt.getId() + " erstellt um " + produkt.getErstellt() + "!");
  }

  public List<ProduktDTO> getAllProdukte() {
    return produktRepository.findAll().stream().map(this::toProductDTO).toList();
  }

  public void updateProdukt(ProduktDTO produktDTO) {
    Produkt produkt = produktRepository.findById(produktDTO.getId()).orElse(null);
    if (produkt != null) {
      produkt.setName(produktDTO.getName());
      produkt.setMarke(produktDTO.getMarke());
      produkt.setGewicht(produktDTO.getGewicht());
      produkt.setBeschreibung(produktDTO.getBeschreibung());
      produkt.setPreis(produktDTO.getPreis());
      produkt.setBildUrl(produktDTO.getBildUrl());
      produktRepository.save(produkt);
      log.info("Produkt " + produkt.getId() + " modifiziert um " + produkt.getModifiziert() + "!");
    }
  }

  private ProduktDTO toProductDTO(Produkt produkt) {
    return ProduktDTO.builder()
        .id(produkt.getId())
        .name(produkt.getName())
        .marke(produkt.getMarke())
        .gewicht(produkt.getGewicht())
        .beschreibung(produkt.getBeschreibung())
        .preis(produkt.getPreis())
        .bildUrl(produkt.getBildUrl())
        .build();
  }
}
