package wild.yellow.travelwishlistbackend.api.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConsumerRequest {

    @NotNull(message = "Name cannot be empty")
    private String username;

    @NotNull(message = "Password cannot be empty")
    private String password;
}
