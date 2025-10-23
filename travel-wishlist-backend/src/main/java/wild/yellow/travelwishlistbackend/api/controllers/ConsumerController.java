package wild.yellow.travelwishlistbackend.api.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import wild.yellow.travelwishlistbackend.api.dtos.requests.ConsumerRequest;
import wild.yellow.travelwishlistbackend.api.dtos.responses.ConsumerDto;
import wild.yellow.travelwishlistbackend.api.services.ConsumerService;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    private final ConsumerService consumerService;

    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping("/registration")
    public ResponseEntity<ConsumerDto> createConsumer(
            @RequestBody @Valid ConsumerRequest consumerRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(consumerService.createConsumer(consumerRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumerDto> getConsumerById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(consumerService.getConsumerById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ConsumerDto> getConsumerByUsername(@PathVariable("username") String username) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(consumerService.getConsumerByUsername(username));
    }

    @GetMapping("/me")
    public ResponseEntity<ConsumerDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        logger.info("Getting current user: {}", username);
        return ResponseEntity.status(HttpStatus.OK)
                .body(consumerService.getConsumerByUsername(username));
    }
}
