package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestControllerV1 {
    private final UserService userService;

    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }


}
