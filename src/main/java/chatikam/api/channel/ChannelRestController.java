package chatikam.api.channel;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import chatikam.domain.model.ChatChannel;
import chatikam.domain.service.channel.ChatChannelService;

@RestController
@RequestMapping("api/channels")
public class ChannelRestController {
    @Inject
    ChatChannelService chatChannelService;
    
    @RequestMapping(method = RequestMethod.GET)
    List<ChatChannel> getChannels() {
        return chatChannelService.findAllVisibleChannel();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    ChatChannel postChannels(@RequestBody ChatChannel chatChannel) {
        return chatChannelService.createNewChannel(chatChannel.getChatChannelName(), chatChannel.isVisible());
    }
    
    @RequestMapping(value = "{channelName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteChannels(@PathVariable("channelName") String channelName) {
       chatChannelService.deleteChannel(channelName);
    }
}
