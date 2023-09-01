package de.janlingen.bestellservice.data;

import de.janlingen.bestellservice.domain.Produkt;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BestellungDTO {
  private Long id;
  private String kundenId;
  private List<ProduktDTO> produktList;
  private Boolean bestellungExcecuted;
}
