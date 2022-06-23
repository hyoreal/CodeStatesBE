package com.codestates.helper.email;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public interface EmailSendable {
    void send(String message) throws InterruptedException;
}
