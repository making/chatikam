package chatikam.app.chat;

import lombok.Data;

@Data
public class MessageDto {
    private String message;
    private String nickname;
    private String channel;
}
