package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.types.ObjectType;
import com.sanyakarpenko.vkbot.types.TaskStatus;
import lombok.Data;

@Data
public class TaskRequestResource {
    private String url;
    private Integer count;

    public Task toTask() {
        Task task = new Task();
        task.setUrl(url);
        task.setCount(count);

        return task;
    }

    public static TaskRequestResource fromTask(Task task) {
        TaskRequestResource resource = new TaskRequestResource();
        resource.setUrl(task.getUrl());
        resource.setCount(task.getCount());

        return resource;
    }
}
