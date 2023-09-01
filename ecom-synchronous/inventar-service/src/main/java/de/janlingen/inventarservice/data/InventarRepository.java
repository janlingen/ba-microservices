package de.janlingen.inventarservice.data;

import de.janlingen.inventarservice.domain.InventarPosition;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author janlingen
 */
@Repository
public interface InventarRepository extends JpaRepository<InventarPosition, Long> {

  Optional<InventarPosition> findByName(String name);
}
