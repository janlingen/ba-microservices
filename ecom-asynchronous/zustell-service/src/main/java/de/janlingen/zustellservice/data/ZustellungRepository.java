package de.janlingen.zustellservice.data;

import de.janlingen.zustellservice.domain.Zustellung;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author janlingen
 */
public interface ZustellungRepository extends JpaRepository<Zustellung, Long> {

}
