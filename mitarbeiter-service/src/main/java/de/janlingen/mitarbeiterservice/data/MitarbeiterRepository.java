package de.janlingen.mitarbeiterservice.data;

import de.janlingen.mitarbeiterservice.domain.Mitarbeiter;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author janlingen
 */
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long> {

  Optional<Mitarbeiter> findByMitarbeiterId(String mitarbeiterId);

  Optional<Mitarbeiter> findByUsername(String username);

}
