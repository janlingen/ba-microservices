package de.janlingen.userservice.data;

import de.janlingen.userservice.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author janlingen
 */
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByKundenId(String kundenId);
  Optional<User> findByUsername(String username);
}
