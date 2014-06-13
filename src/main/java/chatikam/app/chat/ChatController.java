package chatikam.app.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {    
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/sendMessage")
    void sendMessage(MessageDto messageDto) {
        String destination = "/topic/" + messageDto.getChannel();
        simpMessagingTemplate.convertAndSend(messageDto.getChannel(), messageDto);
    }
}
