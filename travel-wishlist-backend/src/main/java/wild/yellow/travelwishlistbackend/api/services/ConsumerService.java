package wild.yellow.travelwishlistbackend.api.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wild.yellow.travelwishlistbackend.api.dtos.JwtAuthenticationDto;
import wild.yellow.travelwishlistbackend.api.dtos.RefreshTokenDto;
import wild.yellow.travelwishlistbackend.api.dtos.requests.ConsumerRequest;
import wild.yellow.travelwishlistbackend.api.dtos.responses.ConsumerDto;
import wild.yellow.travelwishlistbackend.api.factories.ConsumerDtoFactory;
import wild.yellow.travelwishlistbackend.security.jwt.JwtService;
import wild.yellow.travelwishlistbackend.store.entities.ConsumerEntity;
import wild.yellow.travelwishlistbackend.store.repositories.ConsumerRepository;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final ConsumerDtoFactory consumerDtoFactory;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ConsumerService(
            ConsumerRepository consumerRepository,
            ConsumerDtoFactory consumerDtoFactory,
            JwtService jwtService,
            PasswordEncoder passwordEncoder
    ) {
        this.consumerRepository = consumerRepository;
        this.consumerDtoFactory = consumerDtoFactory;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtAuthenticationDto signIn(@Valid ConsumerRequest consumerRequest) throws AuthenticationException {
        ConsumerEntity consumerEntity = findByCredentials(consumerRequest);
        return jwtService.generateAuthToken(consumerEntity.getUsername());
    }

    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception {
        String refreshToken = refreshTokenDto.getRefreshToken();
        if (refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
            ConsumerEntity consumerEntity = findByUsername(jwtService.getUsernameFromToken(refreshToken));
            return jwtService.refreshBaseToken(consumerEntity.getUsername(), refreshToken);
        }

        throw new AuthenticationException("Invalid refresh token");
    }

    public ConsumerDto getConsumerById(Long id) {
        ConsumerEntity consumerEntity = consumerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity by id " + id + " is not found"));

        return consumerDtoFactory.createConsumerDto(consumerEntity);
    }

    public ConsumerDto getConsumerByUsername(String username) {
        ConsumerEntity consumerEntity = consumerRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Entity by username " + username + " is not found"));

        return consumerDtoFactory.createConsumerDto(consumerEntity);
    }

    public ConsumerDto createConsumer(@Valid ConsumerRequest consumerToCreate) {
        var consumerEntity = ConsumerEntity.builder()
                .username(consumerToCreate.getUsername())
                .password(passwordEncoder.encode(consumerToCreate.getPassword()))
                .createdAt(LocalDateTime.now())
                .build();

        var consumerToSave = consumerRepository.save(consumerEntity);
        return consumerDtoFactory.createConsumerDto(consumerToSave);
    }

    private ConsumerEntity findByCredentials(@Valid ConsumerRequest consumerRequest) throws AuthenticationException {
        Optional<ConsumerEntity> consumerEntity = consumerRepository.findByUsername(consumerRequest.getUsername());
        if (consumerEntity.isPresent()) {
            ConsumerEntity consumerEntityToReturn = consumerEntity.get();
            if (passwordEncoder.matches(consumerRequest.getPassword(), consumerEntityToReturn.getPassword())) {
                return consumerEntityToReturn;
            }
        }

        throw new AuthenticationException("Username or password is not correct");
    }

    private ConsumerEntity findByUsername(String username) throws Exception {
        return consumerRepository.findByUsername(username)
                .orElseThrow(() -> new Exception(String.format("Username %s is not found", username)));
    }
}
