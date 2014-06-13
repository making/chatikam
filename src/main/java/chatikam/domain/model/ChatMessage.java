package chatikam.domain.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChatMessage {
    private final String body;
    private final LocalDateTime chatDateTime;
    private final ChatUser from;
    private final ChatChannel chanel;
}
