package wild.yellow.travelwishlistbackend.api.factories;

import org.springframework.stereotype.Component;
import wild.yellow.travelwishlistbackend.api.dtos.responses.ConsumerDto;
import wild.yellow.travelwishlistbackend.store.entities.ConsumerEntity;

@Component
public class ConsumerDtoFactory {

    public ConsumerDto createConsumerDto(ConsumerEntity consumerEntity) {
        return ConsumerDto.builder()
                .id(consumerEntity.getId())
                .username(consumerEntity.getUsername())
                .createdAt(consumerEntity.getCreatedAt())
                .build();
    }
}
