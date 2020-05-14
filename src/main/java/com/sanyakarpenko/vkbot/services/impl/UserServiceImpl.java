package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.entities.Role;
import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.entities.User;
import com.sanyakarpenko.vkbot.repositories.RoleRepository;
import com.sanyakarpenko.vkbot.repositories.UserRepository;
import com.sanyakarpenko.vkbot.services.UserService;
import com.sanyakarpenko.vkbot.types.UserStatus;
import com.sanyakarpenko.vkbot.utils.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addUser(User user) {
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleRepository.findByName("ROLE_USER"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(UserStatus.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("IN addUser - user : {} successfully added", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> findUsers() {
        List<User> result = userRepository.findAll();
        log.info("IN findUsers - {} users found", result.size());
        return result;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if(user.getStatus() == UserStatus.DELETED) {
            return null;
        }

        log.info("IN findUserByUsername - {} found by username: {}", user, username);
        return user;
    }

    @Override
    public User findCurrentUser() {
        User user = findUserByUsername(Helper.getUsername());
        return user;
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if(user.getStatus() == UserStatus.DELETED) {
                return null;
            }

            log.info("IN findUserById - {} found by id: {}", user, id);
            return user;
        }

        log.warn("IN findUserById - no user found by id: {}", id);
        return null;
    }

    @Override
    public User setStatus(UserStatus userStatus) {
        User user = userRepository.findByUsername(Helper.getUsername());

        user.setStatus(userStatus);

        User savedUser = userRepository.save(user);
        log.info("IN setStatus - user : {} successfully updated", savedUser);

        return savedUser;
    }
}
