package com.sanyakarpenko.vkbot.resources;

import lombok.Data;

@Data
public class ErrorResponseResource {
    private Long errorCode;
    private String errorMessage;
}
