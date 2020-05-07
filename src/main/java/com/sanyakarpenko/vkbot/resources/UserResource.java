package com.sanyakarpenko.vkbot.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sanyakarpenko.vkbot.entities.User;
import lombok.Data;

@Data
public class UserResource {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        return user;
    }

    public static UserResource fromUser(User user) {
        UserResource userResource = new UserResource();
        userResource.setId(user.getId());
        userResource.setUsername(user.getUsername());
        userResource.setFirstName(user.getFirstName());
        userResource.setLastName(user.getLastName());
        userResource.setEmail(user.getEmail());

        return userResource;
    }
}
