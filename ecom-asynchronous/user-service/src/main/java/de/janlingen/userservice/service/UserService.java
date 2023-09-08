package de.janlingen.userservice.service;

import de.janlingen.userservice.data.LieferdatenDTO;
import de.janlingen.userservice.data.OutboxMessage;
import de.janlingen.userservice.data.OutboxRepository;
import de.janlingen.userservice.data.UserDTO;
import de.janlingen.userservice.data.UserRepository;
import de.janlingen.userservice.domain.User;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author janlingen
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final OutboxRepository outboxRepository;
  private final TransactionTemplate transaction;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public String createUser(UserDTO userDTO) {
    User user = User.builder()
        .username(userDTO.getUsername())
        .passwort(userDTO.getPasswort())
        .kundenId(UUID.randomUUID().toString())
        .vorname(userDTO.getVorname())
        .nachname(userDTO.getNachname())
        .postleitzahl(userDTO.getPostleitzahl())
        .stadt(userDTO.getStadt())
        .strasse(userDTO.getStrasse())
        .erstellt(LocalDateTime.now())
        .modifiziert(LocalDateTime.now())
        .build();
    OutboxMessage message = OutboxMessage.builder()
        .id(UUID.randomUUID().toString())
        .zielService("bezahl-service")
        .inhalt(user.getKundenId())
        .build();

    transaction.executeWithoutResult(transStatus -> {
      userRepository.save(user);
      outboxRepository.save(message);
    });

    kafkaTemplate.send(message.getZielService(), message.getInhalt());

    outboxRepository.deleteById(message.getId());

    log.info("User " + user.getKundenId() + " erstellt um " + user.getErstellt() + "!");
    return user.getKundenId();
  }

  public void updateUser(UserDTO userDTO) {
    User user = userRepository.findByKundenId(userDTO.getKundenId())
        .orElse(null);
    if (user != null) {
      user.setUsername(userDTO.getUsername());
      user.setPasswort(userDTO.getPasswort());
      user.setVorname(userDTO.getVorname());
      user.setNachname(userDTO.getNachname());
      user.setPostleitzahl(userDTO.getPostleitzahl());
      user.setStadt(userDTO.getStadt());
      user.setStrasse(userDTO.getStrasse());
      user.setModifiziert(LocalDateTime.now());
      userRepository.save(user);
      log.info("User " + user.getKundenId() + " modifiziert um "
          + user.getModifiziert() + "!");
    }
  }

  public UserDTO getUser(String kundenId) {
    User user = userRepository.findByKundenId(kundenId).orElse(null);
    if (user != null) {
      return UserDTO.builder()
          .kundenId(user.getKundenId())
          .vorname(user.getVorname())
          .nachname(user.getNachname())
          .postleitzahl(user.getPostleitzahl())
          .stadt(user.getStadt())
          .strasse(user.getStrasse())
          .hausnummer(user.getHausnummer())
          .build();
    }
    return null;
  }

  public String login(String username, String passwort) {
    User user = userRepository.findByUsername(username).orElse(null);
    if (user != null) {
      System.out.println("test");
      if (user.getPasswort().equals(passwort)) {
        return user.getKundenId();
      }
    }
    System.out.println("test1");
    return null;
  }

  public LieferdatenDTO getLieferdaten(String kundenId) {
    User user = userRepository.findByKundenId(kundenId).orElse(null);
    if (user != null) {
      return LieferdatenDTO.builder()
          .vorname(user.getVorname())
          .nachname(user.getNachname())
          .postleitzahl(user.getPostleitzahl())
          .stadt(user.getStadt())
          .strasse(user.getStrasse())
          .build();
    }
    return null;
  }

}
