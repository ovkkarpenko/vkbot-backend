package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    List<User> findUsers();

    User findUserByUsername(String username);

    User findUserById(Long id);

    void deleteUser(Long id);
}
