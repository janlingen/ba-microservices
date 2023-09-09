package de.janlingen.userservice.data;

import de.janlingen.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author janlingen
 */
public interface OutboxRepository extends JpaRepository<OutboxMessage, String> {

}
