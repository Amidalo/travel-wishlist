package wild.yellow.travelwishlistbackend.api.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DestinationRequest {

    @NotNull(message = "Name cannot be empty")
    @Size(max = 150, message = "Name cannot exceed 150 characters")
    private String name;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Consumer ID cannot be empty")
    private Long consumerId;

}
