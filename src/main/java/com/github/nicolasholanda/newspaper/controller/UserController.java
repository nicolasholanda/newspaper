package com.github.nicolasholanda.newspaper.controller;

import com.github.nicolasholanda.newspaper.model.User;
import com.github.nicolasholanda.newspaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public User save(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping("{id}")
    public User findById(@PathVariable String id) {
        return service.findById(id);
    }
}
