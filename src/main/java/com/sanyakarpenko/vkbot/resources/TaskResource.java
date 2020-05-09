package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.types.ObjectType;
import com.sanyakarpenko.vkbot.types.TaskStatus;
import lombok.Data;

@Data
public class TaskResource {
    private Long id;
    private String url;
    private Integer count;
    private TaskStatus status;
    private ObjectType objectType;

    public Task toTask() {
        Task task = new Task();
        task.setId(id);
        task.setUrl(url);
        task.setCount(count);
        task.setStatus(status);
        task.setObjectType(objectType);

        return task;
    }

    public static TaskResource fromTask(Task task) {
        TaskResource resource = new TaskResource();
        resource.setId(task.getId());
        resource.setUrl(task.getUrl());
        resource.setCount(task.getCount());
        resource.setStatus(task.getStatus());
        resource.setObjectType(task.getObjectType());

        return resource;
    }
}
