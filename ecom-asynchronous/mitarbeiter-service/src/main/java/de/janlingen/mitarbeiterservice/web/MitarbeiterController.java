package de.janlingen.mitarbeiterservice.web;

import de.janlingen.mitarbeiterservice.data.MitarbeiterDTO;
import de.janlingen.mitarbeiterservice.service.MitarbeiterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author janlingen
 */
@RestController
@RequestMapping("/api/mitarbeiter/v1")
public class MitarbeiterController {

  private final MitarbeiterService mitarbeiterService;

  public MitarbeiterController(MitarbeiterService mitarbeiterService) {
    this.mitarbeiterService = mitarbeiterService;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public String createMitarbeiter(@RequestBody MitarbeiterDTO mitarbeiterDTO) {
    try {
      String mitarbeiterId = mitarbeiterService.createMitarbeiter(mitarbeiterDTO);
      return "{ \"mitarbeiterId\":\"" + mitarbeiterId + "\"}";
    } catch (Exception ex) {
      if (ex.toString().contains("Duplicate")) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Mitarbeiter mit der MitarbeiterId existiert bereits!", ex);
      }
    }
    return null;
  }

  @PostMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public void updateMitarbeiter(@RequestBody MitarbeiterDTO mitarbeiterDTO) {
    mitarbeiterService.updateMitarbeiter(mitarbeiterDTO);
  }


  @GetMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public String login(@RequestParam String username, @RequestParam String passwort) {
    String mitarbeiterId = mitarbeiterService.login(username, passwort);
    return "{ \"mitarbeiterId\":\"" + mitarbeiterId + "\"}";
  }
}
