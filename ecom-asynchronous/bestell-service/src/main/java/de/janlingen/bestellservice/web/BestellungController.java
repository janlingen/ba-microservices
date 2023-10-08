package de.janlingen.bestellservice.web;

import de.janlingen.bestellservice.data.BestellungDTO;
import de.janlingen.bestellservice.data.ProduktDTO;
import de.janlingen.bestellservice.service.BestellungService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author janlingen
 */

@RestController
@RequestMapping("/api/bestell/v1")
public class BestellungController {

  private final BestellungService bestellungService;

  public BestellungController(BestellungService bestellungService) {
    this.bestellungService = bestellungService;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public BestellungDTO createBestellung(@RequestBody String kundenId) {
    return bestellungService.createBestellung(kundenId);
  }

  @PostMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public BestellungDTO updateBestellung(@RequestBody UpdateContainer updateContainer) {
    ProduktDTO produktDTO = ProduktDTO.builder()
        .name(updateContainer.name())
        .anzahl(updateContainer.anzahl())
        .preis(updateContainer.preis()).build();
    String kundenId = updateContainer.kundenId();
    Long bestellungId = updateContainer.bestellungId();
    System.out.println("tester");
    return bestellungService.updateBestellung(produktDTO, kundenId, bestellungId);
  }

  @PostMapping("/finish")
  @ResponseStatus(HttpStatus.OK)
  public void finishBestellung(@RequestBody FinishContainer finishContainer) {
    bestellungService.finishBestellung(finishContainer.bestellungId(),
        finishContainer.kundenId());

  }


  record UpdateContainer(String name, Double preis, Long anzahl, String kundenId,
                         Long bestellungId) {

  }

  record FinishContainer(String kundenId, Long bestellungId) {

  }

  record ZustellungContainer(String kundenId, List<ProduktDTO> produktDTOList) {

  }

}
