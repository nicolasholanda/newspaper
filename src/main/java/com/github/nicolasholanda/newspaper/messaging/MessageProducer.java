package com.github.nicolasholanda.newspaper.messaging;

import com.github.nicolasholanda.newspaper.model.News;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private SqsTemplate queueMessagingTemplate;

    public void send(News news) {
        var message = MessageBuilder.withPayload(news).build();
        queueMessagingTemplate.send(message);
    }
}
