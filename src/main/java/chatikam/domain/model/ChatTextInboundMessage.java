package chatikam.domain.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import lombok.Data;

import java.io.IOException;
import java.net.URLDecoder;

@Data
public class ChatTextInboundMessage {
    @JsonDeserialize(using = MessageDeserializer.class)
    private String message;

    public static class MessageDeserializer extends StdScalarDeserializer<String> {

        public MessageDeserializer() {
            super(String.class);
        }

        @Override
        public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            return URLDecoder.decode(jp.getValueAsString(), "UTF-8");
        }
    }
}
