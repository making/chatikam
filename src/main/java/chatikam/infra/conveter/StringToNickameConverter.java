package chatikam.infra.conveter;


import chatikam.domain.model.Nickname;
import org.springframework.core.convert.converter.Converter;

import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class StringToNickameConverter implements Converter<String, Nickname> {
    @Override
    public Nickname convert(String source) {
        try {
            return Nickname.of(URLDecoder.decode(source, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new UncheckedIOException(e);
        }
    }
}
