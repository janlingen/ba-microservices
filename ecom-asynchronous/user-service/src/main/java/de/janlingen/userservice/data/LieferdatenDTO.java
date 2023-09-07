package de.janlingen.userservice.data;

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
public class LieferdatenDTO {

  private String vorname;
  private String nachname;
  private String postleitzahl;
  private String stadt;
  private String strasse;

}
