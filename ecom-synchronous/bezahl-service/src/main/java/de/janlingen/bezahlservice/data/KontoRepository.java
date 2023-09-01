package de.janlingen.bezahlservice.data;

import de.janlingen.bezahlservice.domain.Konto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author janlingen
 */
public interface KontoRepository extends JpaRepository<Konto, Long> {

  Optional<Konto> findByKundenId(String kundenId);
}
