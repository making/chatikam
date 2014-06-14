package chatikam.domain.model;


import lombok.*;

@RequiredArgsConstructor(staticName = "of")
@Data
public class ChannelName {
    private final String value;
}
