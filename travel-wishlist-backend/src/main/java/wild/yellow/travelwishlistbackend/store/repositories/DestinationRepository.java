package wild.yellow.travelwishlistbackend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wild.yellow.travelwishlistbackend.store.entities.DestinationEntity;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {
}
