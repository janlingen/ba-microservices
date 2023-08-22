package de.janlingen.bestellservice.data;

import de.janlingen.bestellservice.domain.Bestellung;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author janlingen
 */
public interface BestellungRepository extends JpaRepository<Bestellung, Long> {

  List<Bestellung> findAllByKundenId(String kundenId);

}
