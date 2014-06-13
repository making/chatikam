package chatikam.domain.service.channel;

import java.util.List;

import chatikam.domain.model.ChatChannel;

public interface ChatChannelService {
    List<ChatChannel> findAllVisibleChannel();
    ChatChannel createNewChannel(String channelName, boolean isVisible);
    ChatChannel createNewChannel(String channelName, boolean isVisible, String channelRawPassword);
    void deleteChannel(String channelName);
}
