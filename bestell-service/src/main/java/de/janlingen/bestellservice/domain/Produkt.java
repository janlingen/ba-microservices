package de.janlingen.bestellservice.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author janlingen
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Produkt {

  private String name;
  private Double preis;
  private Long anzahl;

  public Double getGesamtwert(){
    return this.getAnzahl() * this.getPreis();
  }
}
