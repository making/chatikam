package chatikam.domain.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Data
public class SessionId {
    private final String value;
}
