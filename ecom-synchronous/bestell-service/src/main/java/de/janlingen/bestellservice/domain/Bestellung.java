package de.janlingen.bestellservice.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author janlingen
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Bestellung {

  @Id
  @GeneratedValue
  private Long id;
  private String kundenId;
  @ElementCollection(fetch = FetchType.EAGER)
  private List<Produkt> produktList;
  private Boolean bestellungExcecuted;
  @CreatedDate
  private LocalDateTime erstellt;
  @LastModifiedDate
  private LocalDateTime modifiziert;
}

