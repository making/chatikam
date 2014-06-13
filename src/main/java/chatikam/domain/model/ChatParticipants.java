package chatikam.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChatParticipants {
    private final List<ChatUser> users = new ArrayList<>();

    public synchronized ChatParticipants add(ChatUser user) {
        users.add(user);
        return this;
    }
}
