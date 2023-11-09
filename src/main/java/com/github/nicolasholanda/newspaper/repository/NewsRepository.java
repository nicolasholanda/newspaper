package com.github.nicolasholanda.newspaper.repository;

import com.github.nicolasholanda.newspaper.model.News;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface NewsRepository extends CrudRepository<News, String> {
}
