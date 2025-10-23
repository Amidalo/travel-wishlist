package wild.yellow.travelwishlistbackend.api.dtos;

import lombok.Data;

@Data
public class JwtAuthenticationDto {

    private String token;

    private String refreshToken;
}
