package de.janlingen.inventarservice.web;

import de.janlingen.inventarservice.data.InventarPositionDTO;
import de.janlingen.inventarservice.service.InventarService;
import java.util.List;
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
@RequestMapping("/api/inventar/v1")
public class InventarController {

  private final InventarService inventarService;

  public InventarController(InventarService inventarService) {
    this.inventarService = inventarService;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public void createPosition(@RequestBody InventarPositionDTO inventarPositionDTO) {
    System.out.println("hier auch");
    try {
      inventarService.createPosition(inventarPositionDTO);
    } catch (Exception ex) {
      if (ex.toString().contains("Duplicate")) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Position mit dem Namen existiert bereits!", ex);
      }
    }
  }

  @GetMapping("/getAll")
  @ResponseStatus(HttpStatus.OK)
  public List<InventarPositionDTO> getAllPositionen() {
    return inventarService.getAllPositionen();
  }

  @PostMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public void updatePosition(@RequestBody InventarPositionDTO inventarPositionDTO) {
    inventarService.updatePosition(inventarPositionDTO);
  }

  @PostMapping("/updateList")
  @ResponseStatus(HttpStatus.OK)
  public void updatePositionList(@RequestBody List<InventarPositionDTO> posiList){
    inventarService.updatePositionen(posiList);
  }

  @PostMapping("/checkVerfuegbarkeit")
  @ResponseStatus(HttpStatus.OK)
  public boolean checkVerfuegbarkeit(@RequestBody List<InventarPositionDTO> posiList){
    return inventarService.checkVerfuegbarkeit(posiList);
  }

  //plain text im request body, keine json
  @GetMapping("/getAnzahl")
  @ResponseStatus(HttpStatus.OK)
  public Long getAnzahl(@RequestParam String name) {
    return inventarService.checkPosition(name);
  }

}
