package com.sanyakarpenko.vkbot.resources;

import lombok.Data;

@Data
public class AuthenticationRequestResource {
    private String username;
    private String password;
}
