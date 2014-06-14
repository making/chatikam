package chatikam.app;

import chatikam.domain.model.ChannelName;
import chatikam.domain.model.Nickname;
import chatikam.domain.model.Participants;
import chatikam.domain.model.SessionId;
import org.junit.Test;

public class ParticipantsTest {


    @Test
    public void foo() {
        Participants participants = new Participants();


        participants.join(SessionId.of("xxxx"), ChannelName.of("aaa"), Nickname.of("making"));
        participants.join(SessionId.of("xxxx"), ChannelName.of("bbb"), Nickname.of("making"));
        participants.join(SessionId.of("xxxx"), ChannelName.of("ddd"), Nickname.of("making"));
        participants.join(SessionId.of("yyyy"), ChannelName.of("aaa"), Nickname.of("yamada"));
        participants.join(SessionId.of("yyyy"), ChannelName.of("ccc"), Nickname.of("yamada"));
        participants.join(SessionId.of("zzzz"), ChannelName.of("bbb"), Nickname.of("yamada2"));
        participants.join(SessionId.of("zzzz"), ChannelName.of("ddd"), Nickname.of("yamada2"));
        participants.join(SessionId.of("zzzz"), ChannelName.of("eee"), Nickname.of("yamada2"));


        System.out.println(participants.joinedChannels(SessionId.of("xxxx")));
        System.out.println(participants.joinedChannels(SessionId.of("yyyy")));
        System.out.println(participants.joinedNicknames(ChannelName.of("aaa")));
        System.out.println(participants.joinedNicknames(ChannelName.of("bbb")));
        System.out.println(participants.joinedNicknames(ChannelName.of("ccc")));
        System.out.println(participants);

        System.out.println(participants.leave(SessionId.of("xxxx"), (channelName, nickName) -> {
            System.out.println("bye " + nickName.getValue() + " @ " + channelName.getValue());
        }));
    }
}