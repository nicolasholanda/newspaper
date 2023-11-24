package com.github.nicolasholanda.newspaper.service;

import com.github.nicolasholanda.newspaper.messaging.MessageProducer;
import com.github.nicolasholanda.newspaper.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private MessageProducer messageProducer;

    public void sendNews(News news) {
        messageProducer.send(news);
    }
}
