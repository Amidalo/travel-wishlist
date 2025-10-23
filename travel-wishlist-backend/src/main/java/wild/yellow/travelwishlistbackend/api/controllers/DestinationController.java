package wild.yellow.travelwishlistbackend.api.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wild.yellow.travelwishlistbackend.api.dtos.requests.DestinationRequest;
import wild.yellow.travelwishlistbackend.api.dtos.responses.DestinationDto;
import wild.yellow.travelwishlistbackend.api.services.DestinationService;

import java.util.List;

@RequestMapping("/destinations")
@RestController
public class DestinationController {

    private static final Logger logger = LoggerFactory.getLogger(DestinationController.class);

    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public ResponseEntity<List<DestinationDto>> getDestinations() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(destinationService.getDestinations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationDto> getDestinationById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(destinationService.getDestinationById(id));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<DestinationDto> createDestination(
            @RequestBody @Valid DestinationRequest destinationToCreate
    ) {
        logger.info("The createDestination method is called");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(destinationService.createDestination(destinationToCreate));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<DestinationDto> createDestinationFormData(
            @ModelAttribute @Valid DestinationRequest destinationToCreate
    ) {
        logger.info("The createDestinationFormData method is called");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(destinationService.createDestination(destinationToCreate));
    }

    @PostMapping("/{id}/visited")
    public ResponseEntity<DestinationDto> visitDestination(@PathVariable("id") Long id) {
        logger.info("The visitDestination method is called, id = {}", id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(destinationService.visitDestination(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinationDto> updateDestination(
            @PathVariable("id") Long id, @RequestBody @Valid DestinationRequest destinationToUpdate) {
        logger.info("The updateDestination method is called, id = {}", id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(destinationService.updateDestination(id, destinationToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable("id") Long id) {
        logger.info("The deleteDestination method is called, id = {}", id);

        destinationService.deleteDestination(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
