package chatikam.infra.conveter;


import chatikam.domain.model.ChannelName;
import org.springframework.core.convert.converter.Converter;

import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class StringToChannelNameConverter implements Converter<String, ChannelName> {
    @Override
    public ChannelName convert(String source) {
        try {
            return ChannelName.of(URLDecoder.decode(source, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new UncheckedIOException(e);
        }
    }
}
