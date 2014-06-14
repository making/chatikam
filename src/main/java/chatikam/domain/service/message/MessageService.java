package chatikam.domain.service.message;

import chatikam.domain.model.ChannelName;
import chatikam.domain.model.ChatTextOutboundMessage;
import chatikam.domain.model.Nickname;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class MessageService {
    @Inject
    SimpMessagingTemplate simpMessagingTemplate;

    public void sendJoiningMessage(ChannelName channelName, Nickname nickname) {
        ChatTextOutboundMessage message = new ChatTextOutboundMessage(
                String.format("'%s' joined!", nickname.getValue()),
                Nickname.SYSTEM
        );
        simpMessagingTemplate.convertAndSend("/topic/message/" + channelName.getValue(), message);
        simpMessagingTemplate.convertAndSend("/topic/join/" + channelName.getValue(), nickname);
    }

    public void sendLeavingMessage(ChannelName channelName, Nickname nickname) {
        ChatTextOutboundMessage message = new ChatTextOutboundMessage(
                String.format("'%s' left...", nickname.getValue()),
                Nickname.SYSTEM
        );
        simpMessagingTemplate.convertAndSend("/topic/message/" + channelName.getValue(), message);
        simpMessagingTemplate.convertAndSend("/topic/leave/" + channelName.getValue(), nickname);
    }

    public void sendInstantMessage(ChannelName channelName, Nickname nickname, String message) {
        simpMessagingTemplate.convertAndSend("/topic/message/" + channelName.getValue(), new ChatTextOutboundMessage(
                message,
                nickname
        ));
    }
}
