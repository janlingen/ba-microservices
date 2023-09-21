package de.janlingen.zustellservice.data;

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
  private Long anzahl;

}
