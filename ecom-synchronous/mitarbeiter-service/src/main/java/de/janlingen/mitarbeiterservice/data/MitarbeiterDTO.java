package de.janlingen.mitarbeiterservice.data;

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
public class MitarbeiterDTO {
  private String username;
  private String passwort;
  private String mitarbeiterId;
}