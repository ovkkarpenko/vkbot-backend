package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> findAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
