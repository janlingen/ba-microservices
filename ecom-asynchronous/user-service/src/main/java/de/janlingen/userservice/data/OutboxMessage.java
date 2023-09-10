package de.janlingen.userservice.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author janlingen
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OutboxMessage {

  @Id
  private String id;
  private String zielService;
  private String inhalt;
}
