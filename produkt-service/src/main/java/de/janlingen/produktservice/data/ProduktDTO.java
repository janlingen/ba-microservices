package de.janlingen.produktservice.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author janlingen
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProduktDTO {

  private String id;
  private String name;
  private String marke;
  private Double gewicht;
  private String beschreibung;
  private Double preis;
  private String bildUrl;
}
