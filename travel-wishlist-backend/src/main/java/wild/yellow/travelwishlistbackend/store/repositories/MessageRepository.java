package wild.yellow.travelwishlistbackend.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wild.yellow.travelwishlistbackend.store.entities.MessageEntity;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findAllByOrderByCreatedAtDesc();
}
