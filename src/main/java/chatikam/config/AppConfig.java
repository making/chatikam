package chatikam.config;

import chatikam.infra.conveter.StringToChannelNameConverter;
import chatikam.infra.conveter.StringToNickameConverter;
import chatikam.infra.conveter.StringToSessionIdConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    StringToNickameConverter stringToNickameConverter() {
        return new StringToNickameConverter();
    }

    @Bean
    StringToChannelNameConverter stringToChannelNameConverter() {
        return new StringToChannelNameConverter();
    }

    @Bean
    StringToSessionIdConverter stringToSessionIdConverter() {
        return new StringToSessionIdConverter();
    }
}
