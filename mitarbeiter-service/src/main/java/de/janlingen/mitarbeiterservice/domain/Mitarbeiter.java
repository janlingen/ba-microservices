package de.janlingen.mitarbeiterservice.domain;

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
public class Mitarbeiter {
  @Id
  @GeneratedValue
  private Long id;
  @Column(unique=true)
  private String username;
  private String passwort;
  @Column(unique=true)
  private String mitarbeiterId;
  @CreatedDate
  private LocalDateTime erstellt;
  @LastModifiedDate
  private LocalDateTime modifiziert;
}
