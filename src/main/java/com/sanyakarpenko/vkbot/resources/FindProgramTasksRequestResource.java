package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.types.ObjectType;
import com.sanyakarpenko.vkbot.types.TaskType;
import lombok.Data;

@Data
public class FindProgramTasksRequestResource {
    private Long accountId;
    private String bindingKey;
    private TaskType taskType;
}
