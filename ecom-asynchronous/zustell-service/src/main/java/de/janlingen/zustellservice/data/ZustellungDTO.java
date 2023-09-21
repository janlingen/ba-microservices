package de.janlingen.zustellservice.data;

import de.janlingen.zustellservice.domain.Produkt;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class ZustellungDTO {

  private Long id;
  private String kundenId;
  private List<ProduktDTO> produktDTOList;
}
