package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.User;
import com.sanyakarpenko.vkbot.types.UserStatus;

import java.util.List;

public interface UserService {
    User addUser(User user);

    List<User> findUsers();

    User findUserByUsername(String username);

    User findUserById(Long id);

    User setStatus(UserStatus userStatus);
}
