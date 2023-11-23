package com.github.nicolasholanda.newspaper.utils;

import com.github.nicolasholanda.newspaper.model.News;

import java.util.Optional;

import static java.time.format.DateTimeFormatter.ofPattern;

public class NewsUtils {

    public static String formatNews(News news) {
        var template = "*%s*\n\n*Data:* %s\n\n%s";
        var formattedDate = ofPattern("dd/MM/yyyy HH:mm:ss").format(news.getCreatedAt());

        return String.format(template, news.getTitle(), formattedDate, news.getDescription());
    }
}
