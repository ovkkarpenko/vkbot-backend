package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.types.ObjectType;
import com.sanyakarpenko.vkbot.types.TaskStatus;
import com.sanyakarpenko.vkbot.types.TaskType;
import lombok.Data;

@Data
public class TaskRequestResource {
    private String name;
    private String url;
    private Integer count;
    private Boolean runTaskAfterCreation;
    private TaskType taskType;

    public Task toTask() {
        Task task = new Task();
        task.setName(name);
        task.setUrl(url);
        task.setCount(count);
        task.setTaskType(taskType);

        return task;
    }

    public static TaskRequestResource fromTask(Task task) {
        TaskRequestResource resource = new TaskRequestResource();
        resource.setName(task.getName());
        resource.setUrl(task.getUrl());
        resource.setCount(task.getCount());
        resource.setTaskType(task.getTaskType());

        return resource;
    }
}
