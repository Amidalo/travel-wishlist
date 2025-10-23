package wild.yellow.travelwishlistbackend.api.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wild.yellow.travelwishlistbackend.api.dtos.responses.DestinationDto;
import wild.yellow.travelwishlistbackend.store.entities.DestinationEntity;

@Component
public class DestinationDtoFactory {

    private final ConsumerDtoFactory consumerDtoFactory;

    @Autowired
    public DestinationDtoFactory(ConsumerDtoFactory consumerDtoFactory) {
        this.consumerDtoFactory = consumerDtoFactory;
    }

    public DestinationDto createDestinationDto(DestinationEntity destinationEntity) {
        return DestinationDto.builder()
                .id(destinationEntity.getId())
                .name(destinationEntity.getName())
                .description(destinationEntity.getDescription())
                .status(destinationEntity.getStatus())
                .createdAt(destinationEntity.getCreatedAt())
                .consumer(consumerDtoFactory.createConsumerDto(destinationEntity.getConsumer()))
                .build();
    }
}
