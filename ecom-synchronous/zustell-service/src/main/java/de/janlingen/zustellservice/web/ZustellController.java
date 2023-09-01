package de.janlingen.zustellservice.web;

import de.janlingen.zustellservice.data.ZustellungDTO;
import de.janlingen.zustellservice.service.ZustellService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author janlingen
 */
@RestController
@RequestMapping("/api/zustell/v1")
public class ZustellController {

  private final ZustellService zustellService;

  public ZustellController(ZustellService zustellService) {
    this.zustellService = zustellService;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public void createZustellung(@RequestBody ZustellungDTO zustellungDTO){
    zustellService.createZustellung(zustellungDTO);
  }

  @PostMapping("/finish")
  @ResponseStatus(HttpStatus.OK)
  public void finishZustellung(@RequestBody Long zustellungId){
    zustellService.finishZustellung(zustellungId);
  }

  @GetMapping("/getAll")
  @ResponseStatus(HttpStatus.OK)
  public List<ZustellungDTO> getAllZustellugen(){
    return zustellService.getAllZustellungen();
  }

}
