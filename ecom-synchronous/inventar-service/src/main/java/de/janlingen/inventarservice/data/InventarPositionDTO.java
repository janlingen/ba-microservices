package de.janlingen.inventarservice.data;

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
public class InventarPositionDTO {

  private String name;
  private Long anzahl;
}
