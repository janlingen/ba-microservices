package de.janlingen.bestellservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.janlingen.bestellservice.data.BestellungDTO;
import de.janlingen.bestellservice.data.ProduktDTO;
import de.janlingen.bestellservice.service.BestellungService;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
  public BestellungDTO finishBestellung(@RequestBody FinishContainer finishContainer)
      throws JsonProcessingException {
    Boolean inventarCheck = false;
    Boolean kontoCheck = false;
    BestellungDTO bestellungDTO = bestellungService.getBestellung(finishContainer.bestellungId());

    RestTemplate restTemplate = new RestTemplate();
    // inventar nach verfügbarkeit anfrangen
    HttpHeaders headers1 = new HttpHeaders();
    headers1.setContentType(MediaType.APPLICATION_JSON);
    String url1 = "http://localhost:8080/api/inventar/v1/checkVerfuegbarkeit";
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json1 = ow.writeValueAsString(bestellungDTO.getProduktList());
    HttpEntity<String> requestEntity1 = new HttpEntity<>(json1, headers1);
    ResponseEntity<Boolean> result1 = restTemplate.postForEntity(url1, requestEntity1,
        Boolean.class);
    inventarCheck = result1.getBody();

    // bezahlservice nach vorhandenem geld anfragen
    HttpHeaders headers2 = new HttpHeaders();
    headers2.setContentType(MediaType.APPLICATION_JSON);
    String url2 =
        "http://localhost:8080/api/bezahl/v1/getKontostand?kundenId=" + finishContainer.kundenId();
    ResponseEntity<Double> result2 = restTemplate.getForEntity(url2, Double.class);
    double kontostand =
        result2.getBody() - bestellungService.getBestellungskosten(finishContainer.bestellungId());
    kontoCheck = (kontostand) > 0;
    System.out.println(kontostand);

    if (kontoCheck && inventarCheck) {
      // inventar abzug
      HttpHeaders headers4 = new HttpHeaders();
      headers4.setContentType(MediaType.APPLICATION_JSON);
      String url4 = "http://localhost:8080/api/inventar/v1/updateList";
      ObjectWriter ow3 = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String json4 = ow3.writeValueAsString(bestellungDTO.getProduktList());
      HttpEntity<String> requestEntity4 = new HttpEntity<>(json4, headers4);
      ResponseEntity<Boolean> result4 = restTemplate.postForEntity(url4, requestEntity1,
          Boolean.class);

      // konto abzug
      HttpHeaders headers5 = new HttpHeaders();
      headers5.setContentType(MediaType.APPLICATION_JSON);
      String url5 = "http://localhost:8080/api/bezahl/v1/update";
      String json5 =
          "{\"kundenId\":\"" + finishContainer.kundenId() + "\", \"kontostand\": \"" + kontostand
              + "\"}";
      HttpEntity<String> requestEntity5 = new HttpEntity<>(json5, headers5);
      ResponseEntity<Boolean> result5 = restTemplate.postForEntity(url5, requestEntity5,
          Boolean.class);

      // zustellservice bestellung übermitteln
      HttpHeaders headers3 = new HttpHeaders();
      headers3.setContentType(MediaType.APPLICATION_JSON);
      String url3 = "http://localhost:8080/api/zustell/v1/create";
      ObjectWriter ow2 = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String json2 = ow2.writeValueAsString(
          new ZustellungContainer(finishContainer.kundenId(), bestellungDTO.getProduktList()));
      HttpEntity<String> requestEntity2 = new HttpEntity<>(json2, headers3);
      ResponseEntity<Boolean> result3 = restTemplate.postForEntity(url3, requestEntity2,
          Boolean.class);
      return bestellungService.finishBestellung(finishContainer.bestellungId(),
          finishContainer.kundenId());
    }
    return bestellungService.getBestellung(finishContainer.bestellungId());
  }


  record UpdateContainer(String name, Double preis, Long anzahl, String kundenId,
                         Long bestellungId) {

  }

  record FinishContainer(String kundenId, Long bestellungId) {

  }

  record ZustellungContainer(String kundenId, List<ProduktDTO> produktDTOList) {

  }

}
