package com.github.nicolasholanda.newspaper.controller;

import com.github.nicolasholanda.newspaper.model.News;
import com.github.nicolasholanda.newspaper.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService service;

    @PostMapping
    public News save(@RequestBody News news) {
        return service.save(news);
    }
}
