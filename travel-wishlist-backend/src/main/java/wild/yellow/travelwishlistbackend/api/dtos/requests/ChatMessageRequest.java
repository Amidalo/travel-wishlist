package wild.yellow.travelwishlistbackend.api.dtos.requests;

import lombok.Data;

@Data
public class ChatMessageRequest {
    private String content;
    private String sender;
}