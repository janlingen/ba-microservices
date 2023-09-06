package de.janlingen.produktservice.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author janlingen
 */
@Document(value = "produkt")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Produkt {
  @Id
  private String id;
  @Indexed(unique = true)
  private String name;
  private String marke;
  private Double gewicht;
  private String beschreibung;
  private Double preis;
  private String bildUrl;
  @CreatedDate
  private LocalDateTime erstellt;
  @LastModifiedDate
  private LocalDateTime modifiziert;
}


