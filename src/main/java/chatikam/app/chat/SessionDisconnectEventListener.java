package chatikam.app.chat;

import chatikam.domain.model.Participants;
import chatikam.domain.model.SessionId;
import chatikam.domain.service.message.MessageService;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SessionDisconnectEventListener implements ApplicationListener<SessionDisconnectEvent> {
    @Inject
    Participants participants;
    @Inject
    MessageService messageService;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        SessionId sessionId = SessionId.of(event.getSessionId());
        participants.leave(sessionId, messageService::sendLeavingMessage);
    }
}
