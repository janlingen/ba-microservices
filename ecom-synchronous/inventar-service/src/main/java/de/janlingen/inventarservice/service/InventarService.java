package de.janlingen.inventarservice.service;

import de.janlingen.inventarservice.data.InventarPositionDTO;
import de.janlingen.inventarservice.data.InventarRepository;
import de.janlingen.inventarservice.domain.InventarPosition;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author janlingen
 */
@Service
@Slf4j
public class InventarService {

  private final InventarRepository inventarRepository;

  public InventarService(InventarRepository inventarRepository) {
    this.inventarRepository = inventarRepository;
  }

  public void createPosition(InventarPositionDTO inventarPositionDTO) {
    InventarPosition inventarPosition = InventarPosition.builder()
        .name(inventarPositionDTO.getName())
        .anzahl(0L)
        .erstellt(LocalDateTime.now())
        .modifiziert(LocalDateTime.now())
        .build();
    inventarRepository.save(inventarPosition);
    log.info(
        "Position " + inventarPosition.getName() + " erstellt um " + inventarPosition.getErstellt()
            + "!");
  }

  public List<InventarPositionDTO> getAllPositionen() {
    List<InventarPositionDTO> inventarPositionDTOList = inventarRepository.findAll().stream()
        .map(this::toInventarPositionDTO).toList();
    return inventarPositionDTOList;
  }

  public Boolean checkVerfuegbarkeit(List<InventarPositionDTO> posiList) {
    for (InventarPositionDTO posi : posiList) {
      if (posi.getAnzahl() > 0) {
        if (checkPosition(posi.getName()) - posi.getAnzahl() >= 0) {
        } else {
          return false;
        }
      }
    }
    return true;
  }

  public void updatePosition(InventarPositionDTO inventarPositionDTO) {
    InventarPosition inventarPosition = inventarRepository.findByName(inventarPositionDTO.getName())
        .orElse(null);
    if (inventarPosition != null & inventarPositionDTO.getAnzahl() >= 0) {
      inventarPosition.setName(inventarPositionDTO.getName());
      inventarPosition.setAnzahl(inventarPositionDTO.getAnzahl());
      inventarPosition.setModifiziert(LocalDateTime.now());
      inventarRepository.save(inventarPosition);
      log.info("Position " + inventarPosition.getName() + " modifiziert um "
          + inventarPosition.getModifiziert() + "!");
    }
  }

  public void updatePositionen(List<InventarPositionDTO> inventarPositionDTOList) {
    for (InventarPositionDTO dto : inventarPositionDTOList) {
      InventarPosition inventarPosition = inventarRepository.findByName(dto.getName()).orElse(null);
      if (inventarPosition != null && dto.getAnzahl() >= 0) {
        Long currentAnzahl = inventarPosition.getAnzahl();
        Long updatedAnzahl = currentAnzahl - dto.getAnzahl();
        inventarPosition.setAnzahl(updatedAnzahl);
        inventarPosition.setModifiziert(LocalDateTime.now());
        inventarRepository.save(inventarPosition);
        log.info("Position " + inventarPosition.getName() + " aktualisiert auf Anzahl: "
            + inventarPosition.getAnzahl() + " um " + inventarPosition.getModifiziert() + "!");
      }
    }
  }

  public Long checkPosition(String name) {
    InventarPosition inventarPosition = inventarRepository.findByName(name).orElse(null);
    return inventarPosition.getAnzahl();
  }

  private InventarPositionDTO toInventarPositionDTO(InventarPosition position) {
    return InventarPositionDTO.builder()
        .name(position.getName())
        .anzahl(position.getAnzahl())
        .build();
  }
}
