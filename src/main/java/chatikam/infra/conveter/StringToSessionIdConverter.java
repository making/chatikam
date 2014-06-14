package chatikam.infra.conveter;


import chatikam.domain.model.SessionId;
import org.springframework.core.convert.converter.Converter;

public class StringToSessionIdConverter implements Converter<String, SessionId> {
    @Override
    public SessionId convert(String source) {
        return SessionId.of(source);
    }
}
