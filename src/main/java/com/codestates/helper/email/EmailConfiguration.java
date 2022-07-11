package com.codestates.helper.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
public class EmailConfiguration {

    @Primary
    @Bean
    public EmailSendable mockExceptionEmailSendable() {
        return new MockExceptionEmailSendable();
    }

    @Bean
    public EmailSendable simpleEmailSendable() {
        return new SimpleEmailSendable();
    }
}
