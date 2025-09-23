package wild.yellow.travelwishlistbackend.api.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wild.yellow.travelwishlistbackend.api.dtos.requests.DestinationRequest;
import wild.yellow.travelwishlistbackend.api.dtos.responses.DestinationDto;
import wild.yellow.travelwishlistbackend.api.factories.DestinationDtoFactory;
import wild.yellow.travelwishlistbackend.enums.DestinationStatus;
import wild.yellow.travelwishlistbackend.store.entities.DestinationEntity;
import wild.yellow.travelwishlistbackend.store.repositories.DestinationRepository;

import java.util.List;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationDtoFactory destinationDtoFactory;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
        this.destinationDtoFactory = new DestinationDtoFactory();
    }

    public List<DestinationDto> getDestinations() {
        List<DestinationEntity> destinations = destinationRepository.findAll();

        return destinations.stream()
                .map(destinationDtoFactory::createDestinationDto)
                .toList();
    }

    public DestinationDto getDestinationById(Long id) {
        DestinationEntity destinationEntity = destinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity by id " + id + " is not found"));

        return destinationDtoFactory.createDestinationDto(destinationEntity);
    }

    public DestinationDto createDestination(@Valid DestinationRequest destinationToCreate) {
        var destinationEntity = DestinationEntity.builder()
                .name(destinationToCreate.getName())
                .description(destinationToCreate.getDescription())
                .build();

        var destinationToSave = destinationRepository.save(destinationEntity);
        return destinationDtoFactory.createDestinationDto(destinationToSave);
    }

    public DestinationDto updateDestination(Long id, @Valid DestinationRequest destinationToUpdate) {
        DestinationEntity destinationEntity = destinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity by id " + id + " is not found"));

        var destinationToSave = new DestinationEntity(
                destinationEntity.getId(),
                destinationToUpdate.getName(),
                destinationToUpdate.getDescription(),
                destinationEntity.getStatus(),
                destinationEntity.getConsumer(),
                destinationEntity.getCreatedAt()
        );

        var updatedDestination = destinationRepository.save(destinationToSave);
        return destinationDtoFactory.createDestinationDto(updatedDestination);
    }

    public void deleteDestination(Long id) {
        DestinationEntity destinationEntity = destinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity by id " + id + " is not found"));

        destinationRepository.delete(destinationEntity);
    }

    public DestinationDto visitDestination(Long id) {
        DestinationEntity destinationEntity = destinationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity by id " + id + " is not found"));

        if (destinationEntity.getStatus() != DestinationStatus.PLANNED) {
            throw new IllegalStateException("Destination status is already VISITED");
        }

        destinationEntity.setStatus(DestinationStatus.VISITED);
        destinationRepository.save(destinationEntity);

        return destinationDtoFactory.createDestinationDto(destinationEntity);
    }
}
