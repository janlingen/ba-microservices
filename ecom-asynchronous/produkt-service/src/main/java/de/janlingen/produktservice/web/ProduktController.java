package de.janlingen.produktservice.web;

import com.fasterxml.jackson.databind.util.JSONPObject;
import de.janlingen.produktservice.data.ProduktDTO;
import de.janlingen.produktservice.service.ProduktService;
import java.sql.SQLOutput;
import java.util.Collections;
import java.util.List;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author janlingen
 */
@RestController
@RequestMapping("/api/produkt/v1")
public class ProduktController {

  private final ProduktService produktService;

  public ProduktController(ProduktService produktService) {
    this.produktService = produktService;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public void createProdukt(@RequestBody ProduktDTO produktDTO) {
    RestTemplate restTemplate = new RestTemplate();
    try {
      produktService.createProdukt(produktDTO);
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      String url = "http://localhost:8080/api/inventar/v1/create";
      String requestBody = "{\"name\": \"" + produktDTO.getName() + "\"}";
      HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
      restTemplate.postForEntity(url, requestEntity, String.class);
    } catch (Exception ex) {
      if (ex.toString().contains("name dup key")) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Produkt mit dem Namen existiert bereits!", ex);
      }
    }
  }

  @GetMapping("/getAll")
  @ResponseStatus(HttpStatus.OK)
  public List<ProduktDTO> getAllProdukte() {
    return produktService.getAllProdukte();
  }

  @PostMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public void updateProdukt(@RequestBody ProduktDTO produktDTO) {
    produktService.updateProdukt(produktDTO);
  }

}
