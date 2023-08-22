package de.janlingen.bestellservice.data;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author janlingen
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProduktDTO {
  private String name;
  private Double preis;
  private Long anzahl;
}
