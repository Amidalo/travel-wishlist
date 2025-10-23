package wild.yellow.travelwishlistbackend.api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerDto {

    private Long id;

    private String username;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
