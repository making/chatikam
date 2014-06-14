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
    public Collection<Nickname> join(@Header("simpSessionId") String simpSessionId,
                                     @DestinationVariable("channelName") String channelName,
                                     @DestinationVariable("nickname") String nickname) {
        participants.join(SessionId.of(simpSessionId), ChannelName.of(channelName), Nickname.of(nickname));
        messageService.sendJoiningMessage(ChannelName.of(channelName), Nickname.of(nickname));
        return participants.joinedNicknames(ChannelName.of(channelName));
    }

    @MessageMapping("/message/{channelName}")
    public ChatTextOutboundMessage sendMessage(@Header("simpSessionId") String simpSessionId,
                                               @DestinationVariable("channelName") String channelName,
                                               @Payload ChatTextInboundMessage inboundMessage) throws Exception {
        Optional<Nickname> nickname = participants.getNickname(SessionId.of(simpSessionId), ChannelName.of(channelName));
        return new ChatTextOutboundMessage(inboundMessage.getMessage(), nickname.orElse(Nickname.SYSTEM));
    }

}
