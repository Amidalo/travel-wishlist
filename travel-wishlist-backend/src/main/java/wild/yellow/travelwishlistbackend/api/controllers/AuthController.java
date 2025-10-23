package wild.yellow.travelwishlistbackend.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wild.yellow.travelwishlistbackend.api.dtos.JwtAuthenticationDto;
import wild.yellow.travelwishlistbackend.api.dtos.RefreshTokenDto;
import wild.yellow.travelwishlistbackend.api.dtos.requests.ConsumerRequest;
import wild.yellow.travelwishlistbackend.api.services.ConsumerService;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ConsumerService consumerService;

    @Autowired
    public AuthController(final ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationDto> signIn(@RequestBody @Valid ConsumerRequest consumerRequest) {
        try {
            JwtAuthenticationDto jwtAuthenticationDto = consumerService.signIn(consumerRequest);
            return new ResponseEntity<>(jwtAuthenticationDto, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed " + e);
        }
    }

    @PostMapping("/refresh")
    public JwtAuthenticationDto refresh(@RequestBody RefreshTokenDto refreshTokenDto) throws Exception {
        return consumerService.refreshToken(refreshTokenDto);
    }
}
