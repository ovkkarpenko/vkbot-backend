package com.sanyakarpenko.vkbot.resources;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseResource {
    private Long errorCode;
    private String errorMessage;
}
