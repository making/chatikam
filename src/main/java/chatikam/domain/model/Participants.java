package chatikam.domain.model;

import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import lombok.ToString;

import javax.inject.Named;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

@Named
@ToString
public class Participants {
    private final Table<SessionId, ChannelName, Nickname> table = Tables
            .newCustomTable(new ConcurrentHashMap<SessionId, Map<ChannelName, Nickname>>(),
                    () -> new ConcurrentHashMap<ChannelName, Nickname>());

    public Participants join(SessionId sessionId, ChannelName channelName, Nickname nickname) {
        synchronized (table) {
            Set<Nickname> nicknames = new HashSet<>(table.column(channelName).values());
            if (nicknames.contains(nickname)) {
                for (int i = 1; i <= 100; i++) {
                    Nickname alternative = Nickname.of(String.format("%s(%d)", nickname.getValue(), i));
                    if (!nicknames.contains(alternative)) {
                        table.put(sessionId, channelName, alternative);
                        return this;
                    }
                }
                throw new NicknameAlreadyUsedException(nickname, channelName);
            }
            table.put(sessionId, channelName, nickname);
        }
        return this;
    }

    public Set<ChannelName> joinedChannels(SessionId sessionId) {
        Set<ChannelName> joinedChannels = table.row(sessionId).keySet();
        return joinedChannels;
    }

    public Set<ChannelName> availableChannels() {
        Set<ChannelName> channelNames = table.columnKeySet();
        return channelNames;
    }

    public Optional<ChannelName> getChannel(String channelName) {
        if (table.containsColumn(channelName)) {
            return Optional.of(ChannelName.of(channelName));
        }
        return Optional.empty();
    }

    public Collection<Nickname> joinedNicknames(ChannelName channelName) {
        Collection<Nickname> joinedNicknames = table.column(channelName).values();
        return joinedNicknames;
    }


    public Participants leave(SessionId sessionId, BiConsumer<ChannelName, Nickname> leaveCallback) {
        synchronized (table) {
            joinedChannels(sessionId).forEach((channelName) -> {
                Nickname nickname = table.get(sessionId, channelName);
                table.remove(sessionId, channelName);
                if (leaveCallback != null) {
                    leaveCallback.accept(channelName, nickname);
                }
            });
        }
        return this;
    }

    public Optional<Nickname> getNickname(SessionId sessionId, ChannelName channelName) {
        return Optional.ofNullable(table.get(sessionId, channelName));
    }

    public Participants leave(SessionId sessionId) {
        return leave(sessionId, null);
    }

    public Participants clear() {
        synchronized (table) {
            table.clear();
        }
        return this;
    }

    public static class NicknameAlreadyUsedException extends RuntimeException {
        public NicknameAlreadyUsedException(Nickname nickname, ChannelName channelName) {
            super(String.format("'%s' is already used in '%s", nickname.getValue(), channelName.getValue()));
        }
    }
}
