package chatikam.domain.model;


import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public interface UrlEncodable {
    String getValue();

    default String urlEncode() {
        try {
            return URLEncoder.encode(getValue(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UncheckedIOException(e);
        }
    }
}
