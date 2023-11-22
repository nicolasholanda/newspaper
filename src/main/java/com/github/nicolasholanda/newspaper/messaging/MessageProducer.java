package com.github.nicolasholanda.newspaper.messaging;

import com.github.nicolasholanda.newspaper.model.News;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessageProducer {

    @Autowired
    private SqsTemplate queueMessagingTemplate;

    @AfterReturning(value = "execution(* com.github.nicolasholanda.newspaper.service.NewsService.save(..)) && args(news)")
    public void send(News news) {
        var message = MessageBuilder.withPayload(news).build();
        queueMessagingTemplate.send(message);
    }
}
