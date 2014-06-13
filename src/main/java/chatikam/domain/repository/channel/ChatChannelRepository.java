package chatikam.domain.repository.channel;

import org.springframework.data.jpa.repository.JpaRepository;

import chatikam.domain.model.ChatChannel;


public interface ChatChannelRepository extends JpaRepository<ChatChannel, String> {
}
