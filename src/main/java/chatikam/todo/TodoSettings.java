package chatikam.todo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.inject.Named;

@Named
@ConfigurationProperties("todo")
@Data
public class TodoSettings {
    private int maxCount;
}
