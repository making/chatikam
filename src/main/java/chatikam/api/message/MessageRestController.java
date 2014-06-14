package chatikam.api.message;

import chatikam.domain.model.ChannelName;
import chatikam.domain.model.Nickname;
import chatikam.domain.service.message.MessageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("api/messages")
public class MessageRestController {
    @Inject
    MessageService messageService;

    @RequestMapping("{channelName}/{nickname}")
    public void sendInstantMessage(@PathVariable("channelName") ChannelName channelName,
                                   @PathVariable("nickname") String nickname,
                                   @RequestParam("message") String message) {
        messageService.sendInstantMessage(
                channelName,
                Nickname.of('$' + nickname + '$'),
                message);
    }
}
