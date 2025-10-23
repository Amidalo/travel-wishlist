package wild.yellow.travelwishlistbackend.api.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private String content;
    private String sender;
    private LocalDateTime timestamp;
}