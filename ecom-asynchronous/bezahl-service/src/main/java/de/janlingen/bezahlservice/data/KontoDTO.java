package de.janlingen.bezahlservice.data;

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
public class KontoDTO {
  private String kundenId;
  private Double kontostand;
}
