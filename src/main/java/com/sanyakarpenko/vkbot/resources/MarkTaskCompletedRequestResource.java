package com.sanyakarpenko.vkbot.resources;

import lombok.Data;

@Data
public class MarkTaskCompletedRequestResource {
    private Long taskId;
    private Long accountId;
}
