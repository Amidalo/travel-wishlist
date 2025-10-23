package wild.yellow.travelwishlistbackend.api.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import wild.yellow.travelwishlistbackend.api.dtos.requests.ChatMessageRequest;
import wild.yellow.travelwishlistbackend.api.dtos.responses.ChatMessageDto;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDto sendMessage(ChatMessageRequest chatMessage) {
        return ChatMessageDto.builder()
                .content(chatMessage.getContent())
                .sender(chatMessage.getSender())
                .timestamp(java.time.LocalDateTime.now())
                .build();
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageDto addUser(ChatMessageRequest chatMessage) {
        return ChatMessageDto.builder()
                .content(chatMessage.getSender() + " присоединился к чату!")
                .sender("System")
                .timestamp(java.time.LocalDateTime.now())
                .build();
    }
}