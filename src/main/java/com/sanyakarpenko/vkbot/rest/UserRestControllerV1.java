package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.Settings;
import com.sanyakarpenko.vkbot.entities.User;
import com.sanyakarpenko.vkbot.resources.SettingsResource;
import com.sanyakarpenko.vkbot.resources.UserResource;
import com.sanyakarpenko.vkbot.services.SettingsService;
import com.sanyakarpenko.vkbot.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestControllerV1 {
    private final UserService userService;

    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getUser() {
        User user = userService.findCurrentUser();
        return ResponseEntity.ok(UserResource.fromUser(user));
    }
}
