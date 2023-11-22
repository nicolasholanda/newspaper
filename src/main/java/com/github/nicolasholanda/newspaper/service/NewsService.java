package com.github.nicolasholanda.newspaper.service;

import com.github.nicolasholanda.newspaper.model.News;
import com.github.nicolasholanda.newspaper.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repository;

    public News save(News news) {
//        News savedNews = repository.save(news);
        return news;
    }
}
