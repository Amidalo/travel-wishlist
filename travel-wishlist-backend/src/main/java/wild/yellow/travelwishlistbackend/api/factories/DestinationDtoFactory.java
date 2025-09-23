package wild.yellow.travelwishlistbackend.api.factories;

import org.springframework.stereotype.Component;
import wild.yellow.travelwishlistbackend.api.dtos.responses.DestinationDto;
import wild.yellow.travelwishlistbackend.store.entities.DestinationEntity;

@Component
public class DestinationDtoFactory {

    public DestinationDto createDestinationDto(DestinationEntity destinationEntity) {
        return DestinationDto.builder()
                .id(destinationEntity.getId())
                .name(destinationEntity.getName())
                .description(destinationEntity.getDescription())
                .status(destinationEntity.getStatus())
                .createdAt(destinationEntity.getCreatedAt())
                .build();
    }
}
