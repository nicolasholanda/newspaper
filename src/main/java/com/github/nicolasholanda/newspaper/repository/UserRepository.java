package com.github.nicolasholanda.newspaper.repository;

import com.github.nicolasholanda.newspaper.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
