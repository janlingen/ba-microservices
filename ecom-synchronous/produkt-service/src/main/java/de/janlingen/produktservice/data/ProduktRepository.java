package de.janlingen.produktservice.data;

import de.janlingen.produktservice.domain.Produkt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author janlingen
 */
public interface ProduktRepository extends MongoRepository<Produkt, String> {

}
