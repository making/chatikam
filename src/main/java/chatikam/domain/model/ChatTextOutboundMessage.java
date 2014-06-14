package chatikam.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChatTextOutboundMessage {
    private final String message;
    private final Nickname nickname;
}
