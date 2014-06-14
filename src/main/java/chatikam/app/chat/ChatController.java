package chatikam.app.chat;

import chatikam.domain.model.*;
import chatikam.domain.service.message.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

@Controller
public class ChatController {
    @Inject
    Participants participants;
    @Inject
    MessageService messageService;

    @SubscribeMapping("/join/{channelName}/{nickname}")
    public Collection<Nickname> join(@Header("simpSessionId") SessionId simpSessionId,
                                     @DestinationVariable("channelName") ChannelName channelName,
                                     @DestinationVariable("nickname") Nickname nickname) {
        participants.join(simpSessionId, channelName, nickname);
        messageService.sendJoiningMessage(channelName, nickname);
        return participants.joinedNicknames(channelName);
    }

    @MessageMapping("/message/{channelName}")
    public ChatTextOutboundMessage sendMessage(@Header("simpSessionId") SessionId simpSessionId,
                                               @DestinationVariable("channelName") ChannelName channelName,
                                               @Payload ChatTextInboundMessage inboundMessage) {
        Optional<Nickname> nickname = participants.getNickname(simpSessionId, channelName);
        return new ChatTextOutboundMessage(
                inboundMessage.getMessage(),
                channelName,
                nickname.orElse(Nickname.SYSTEM));
    }

}
