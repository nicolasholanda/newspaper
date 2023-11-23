package com.github.nicolasholanda.newspaper.messaging;

import com.github.nicolasholanda.newspaper.model.News;
import com.github.nicolasholanda.newspaper.service.UserService;
import com.github.nicolasholanda.newspaper.service.WhatsappService;
import com.github.nicolasholanda.newspaper.utils.NewsUtils;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessagePolling {

    @Autowired
    private UserService userService;

    @Autowired
    private WhatsappService whatsappService;

    @SqsListener(value = "newspaper", pollTimeoutSeconds = "5")
    public void receiveMessage(@Payload News news) {
        var formattedNews = NewsUtils.formatNews(news);
        userService.findAll().forEach(user ->
            whatsappService.sendTextMessageToPhoneNumber(formattedNews, user.getPhoneNumber())
        );
    }
}
