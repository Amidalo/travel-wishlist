package wild.yellow.travelwishlistbackend.api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wild.yellow.travelwishlistbackend.enums.DestinationStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDto {

    private Long id;

    private String name;

    private String description;

    private DestinationStatus status;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("consumer")
    private ConsumerDto consumer;
}
