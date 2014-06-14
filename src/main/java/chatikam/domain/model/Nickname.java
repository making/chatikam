package chatikam.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Data
public class Nickname {
    private final String value;

    public static final Nickname SYSTEM = Nickname.of("system");
}
