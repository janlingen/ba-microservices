package de.janlingen.bezahlservice.web;

import de.janlingen.bezahlservice.data.KontoDTO;
import de.janlingen.bezahlservice.service.KontoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author janlingen
 */
@RestController
@RequestMapping("/api/bezahl/v1")
public class KontoController {
  private final KontoService kontoService;

  public KontoController(KontoService kontoService) {
    this.kontoService = kontoService;
  }

  @PostMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public void updateKonto(@RequestBody KontoDTO kontoDTO) {
    kontoService.updateKonto(kontoDTO);
  }

  //plain text im request body, keine json
  @GetMapping("/getKontostand")
  @ResponseStatus(HttpStatus.OK)
  public Double getKontostand(@RequestParam String kundenId) {
    return kontoService.checkKontostand(kundenId);
  }
}
