package de.janlingen.inventarservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InventarPosition {

  @Id
  @GeneratedValue
  private Long id;
  @Column(unique=true)
  private String name;
  private Long anzahl;
  @CreatedDate
  private LocalDateTime erstellt;
  @LastModifiedDate
  private LocalDateTime modifiziert;
}
