package chatikam.domain.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Data
public class ChannelName implements UrlEncodable {
    private final String value;

    public boolean isPublic() {
        return !value.startsWith("_");
    }
}
