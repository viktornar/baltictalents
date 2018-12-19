package lt.baltictalents.lesson3.config;

import lt.baltictalents.lesson3.pattern.example.legacy.EmailService;
import lt.baltictalents.lesson3.service.EmailServiceImpl;
import lt.baltictalents.lesson3.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageServiceConfig {
    @Bean
    public MessageService emailService() {
        return new EmailServiceImpl();
    }

    @Bean
    public MessageService smsService() {
        return new EmailServiceImpl();
    }
}
