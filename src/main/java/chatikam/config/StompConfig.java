package chatikam.config;

import chatikam.domain.model.ChannelName;
import chatikam.domain.model.Nickname;
import chatikam.domain.model.SessionId;
import chatikam.infra.conveter.StringToChannelNameConverter;
import chatikam.infra.conveter.StringToNickameConverter;
import chatikam.infra.conveter.StringToSessionIdConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.annotation.support.SimpAnnotationMethodMessageHandler;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.DelegatingWebSocketMessageBrokerConfiguration;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import javax.inject.Inject;
import java.util.List;

@Configuration
//@EnableWebSocketMessageBroker
public class StompConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
        registry.addEndpoint("/like").withSockJS();
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        return super.configureMessageConverters(messageConverters);
    }

    @Configuration
    public static class CustomizedDelegatingWebSocketMessageBrokerConfiguration extends DelegatingWebSocketMessageBrokerConfiguration {
        @Bean
        @Inject
        public SimpAnnotationMethodMessageHandler simpAnnotationMethodMessageHandler(
                StringToNickameConverter stringToNickameConverter,
                StringToChannelNameConverter stringToChannelNameConverter,
                StringToSessionIdConverter stringToSessionIdConverter
        ) {
            SimpAnnotationMethodMessageHandler handler = super.simpAnnotationMethodMessageHandler();
            DefaultConversionService conversionService = new DefaultConversionService();
            conversionService.addConverter(String.class, Nickname.class, stringToNickameConverter);
            conversionService.addConverter(String.class, ChannelName.class, stringToChannelNameConverter);
            conversionService.addConverter(String.class, SessionId.class, stringToSessionIdConverter);
            handler.setConversionService(conversionService);
            return handler;
        }
    }
}
