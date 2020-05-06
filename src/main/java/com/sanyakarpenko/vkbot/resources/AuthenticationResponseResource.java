package com.sanyakarpenko.vkbot.resources;

import lombok.Data;

@Data
public class AuthenticationResponseResource {
    private String username;
    private String token;
}
