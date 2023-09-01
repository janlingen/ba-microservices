package de.janlingen.userservice.data;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author janlingen
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {

  private String username;
  private String passwort;
  private String kundenId;
  private String vorname;
  private String nachname;
  private String postleitzahl;
  private String stadt;
  private String strasse;
  private String hausnummer;
}
