package de.janlingen.zustellservice.service;

import de.janlingen.zustellservice.data.ProduktDTO;
import de.janlingen.zustellservice.data.ZustellungDTO;
import de.janlingen.zustellservice.data.ZustellungRepository;
import de.janlingen.zustellservice.domain.Produkt;
import de.janlingen.zustellservice.domain.Zustellung;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author janlingen
 */
@Service
@Slf4j
public class ZustellService {

  private final ZustellungRepository zustellungRepository;

  public ZustellService(ZustellungRepository zustellungRepository) {
    this.zustellungRepository = zustellungRepository;
  }

  public void createZustellung(ZustellungDTO zustellungDTO) {
    Zustellung zustellung = Zustellung.builder()
        .kundenId(zustellungDTO.getKundenId())
        .produktList(zustellungDTO.getProduktDTOList().stream().map(this::toProdukt).toList())
        .zugestellt(false)
        .erstellt(LocalDateTime.now())
        .modifiziert(LocalDateTime.now())
        .build();
    zustellungRepository.save(zustellung);
    log.info(
        "Zustellung " + zustellung.getId() + " erstellt um " + zustellung.getErstellt()
            + "!");
  }

  public void finishZustellung(Long zustellungId) {
    Zustellung zustellung = zustellungRepository.findById(zustellungId).orElse(null);
    if (zustellung != null) {
      zustellung.setZugestellt(true);
      zustellungRepository.save(zustellung);
      log.info(
          "Zustellung " + zustellung.getId() + " modifiziert um " + zustellung.getErstellt()
              + "!");
    }
  }

  public List<ZustellungDTO> getAllZustellungen() {
    return zustellungRepository.findAll().stream()
        .filter(zustellung -> zustellung.getZugestellt().equals(false)).map(this::toZustellungDTO)
        .toList();
  }

  public ZustellungDTO toZustellungDTO(Zustellung zustellung) {
    return ZustellungDTO.builder()
        .id(zustellung.getId())
        .kundenId(zustellung.getKundenId())
        .produktDTOList(zustellung.getProduktList().stream().map(this::toProduktDTO).toList())
        .build();
  }

  public Produkt toProdukt(ProduktDTO produktDTO) {
    return Produkt.builder().name(produktDTO.getName()).anzahl(produktDTO.getAnzahl()).build();
  }

  public ProduktDTO toProduktDTO(Produkt produkt) {
    return ProduktDTO.builder().name(produkt.getName()).anzahl(produkt.getAnzahl()).build();
  }


}
