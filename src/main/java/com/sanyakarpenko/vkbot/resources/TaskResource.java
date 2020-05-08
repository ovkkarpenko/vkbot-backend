package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.types.TaskType;
import lombok.Data;

@Data
public class TaskResource {
    private Long id;
    private String url;
    private Integer count;
    private TaskType taskType;

    public Task toTask() {
        Task task = new Task();
        task.setId(id);
        task.setUrl(url);
        task.setCount(count);
        task.setTaskType(taskType);

        return task;
    }

    public static TaskResource fromTask(Task task) {
        TaskResource resource = new TaskResource();
        resource.setId(task.getId());
        resource.setUrl(task.getUrl());
        resource.setCount(task.getCount());
        resource.setTaskType(task.getTaskType());

        return resource;
    }
}
