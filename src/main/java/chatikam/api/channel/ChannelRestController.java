package chatikam.api.channel;

import chatikam.domain.model.ChannelName;
import chatikam.domain.model.Participants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping("api/channels")
public class ChannelRestController {
    @Inject
    Participants participants;

    @RequestMapping
    Collection<ChannelName> getChannels() {
        return participants.getPublicChannels();
    }

    @RequestMapping(value = "{channelName}", method = RequestMethod.GET)
    ResponseEntity<ChannelName> getChannel(@PathVariable("channelName") ChannelName channelName) {
        return participants.getChannel(channelName)
                .map(channel -> new ResponseEntity<>(channel, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
