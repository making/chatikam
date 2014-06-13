package chatikam.domain.service.channel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import chatikam.domain.model.ChatChannel;
import chatikam.domain.repository.channel.ChatChannelRepository;

@Named
@Transactional
public class ChatChannelServiceImpl implements ChatChannelService {
    @Inject
    ChatChannelRepository chatChannelRepository;

    @Override
    public List<ChatChannel> findAllVisibleChannel() {
        return chatChannelRepository.findAll();
    }

    @Override
    public ChatChannel createNewChannel(String channelName, boolean isVisible) {
        ChatChannel chatChannel = new ChatChannel(channelName, isVisible, null);
        return chatChannelRepository.save(chatChannel);
    }

    @Override
    public ChatChannel createNewChannel(String channelName, boolean isVisible, String channelRawPassword) {
       throw new UnsupportedOperationException();
    }

    @Override
    public void deleteChannel(String channelName) {
        chatChannelRepository.delete(channelName);
    }
}
