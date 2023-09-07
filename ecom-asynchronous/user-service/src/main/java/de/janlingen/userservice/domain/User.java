package de.janlingen.userservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

  @Id
  @GeneratedValue
  private Long id;
  @Column(unique=true)
  private String username;
  private String passwort;
  @Column(unique=true)
  private String kundenId;
  private String vorname;
  private String nachname;
  private String postleitzahl;
  private String stadt;
  private String strasse;
  private String hausnummer;
  @CreatedDate
  private LocalDateTime erstellt;
  @LastModifiedDate
  private LocalDateTime modifiziert;
}
