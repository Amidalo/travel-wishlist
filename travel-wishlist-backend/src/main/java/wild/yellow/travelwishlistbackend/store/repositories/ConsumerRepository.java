package wild.yellow.travelwishlistbackend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wild.yellow.travelwishlistbackend.store.entities.ConsumerEntity;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<ConsumerEntity, Long> {

    Optional<ConsumerEntity> findByUsername(String username);
}
