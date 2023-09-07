package de.janlingen.userservice.web;

import de.janlingen.userservice.data.LieferdatenDTO;
import de.janlingen.userservice.data.UserDTO;
import de.janlingen.userservice.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author janlingen
 */
@RestController
@RequestMapping("/api/user/v1")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public String createUser(@RequestBody UserDTO userDTO) {
    RestTemplate restTemplate = new RestTemplate();
    try {
      String kundenId = userService.createUser(userDTO);
//      HttpHeaders headers = new HttpHeaders();
//      headers.setContentType(MediaType.APPLICATION_JSON);
//      String url = "http://localhost:8080/api/bezahl/v1/create";
//      String requestBody = "{\"kundenId\": \"" + kundenId + "\"}";
//      HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//      restTemplate.postForEntity(url, requestEntity, String.class);
      return "{ \"kundenId\":\"" + kundenId + "\"}";
    } catch (Exception ex) {
      if (ex.toString().contains("Duplicate")) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "User mit der KundenId existiert bereits!", ex);
      }
    }
    return null;
  }

  @PostMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public void updateUser(@RequestBody UserDTO userDTO) {
    userService.updateUser(userDTO);
  }

  //plain text im request body, keine json
  @GetMapping("/getLieferdaten")
  @ResponseStatus(HttpStatus.OK)
  public LieferdatenDTO getLieferdaten(@RequestBody String kundenId) {
    return userService.getLieferdaten(kundenId);
  }

  //plain text im request body, keine json
  @GetMapping("/getUser")
  @ResponseStatus(HttpStatus.OK)
  public UserDTO getUser(@RequestBody String kundenId) {
    return userService.getUser(kundenId);
  }

  @GetMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public String login(@RequestParam String username, @RequestParam String passwort) {
    String kundenId = userService.login(username, passwort);
    return "{ \"kundenId\":\"" + kundenId + "\"}";
  }
}
