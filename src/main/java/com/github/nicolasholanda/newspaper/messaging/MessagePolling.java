package com.github.nicolasholanda.newspaper.messaging;

import com.github.nicolasholanda.newspaper.model.News;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessagePolling {

    @SqsListener(value = "newspaper", pollTimeoutSeconds = "5")
    public void receiveMessage(@Payload News news) {
        System.out.println(news);
    }
}
