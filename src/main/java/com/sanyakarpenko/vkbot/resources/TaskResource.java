package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.types.ObjectType;
import com.sanyakarpenko.vkbot.types.TaskStatus;
import com.sanyakarpenko.vkbot.types.TaskType;
import lombok.Data;

@Data
public class TaskResource {
    private Long id;
    private String name;
    private String url;
    private Integer count;
    private Integer statsCompleted;
    private TaskStatus status;
    private TaskType taskType;

    public Task toTask() {
        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setUrl(url);
        task.setCount(count);
        task.setStatsCompleted(statsCompleted);
        task.setStatus(status);
        task.setTaskType(taskType);

        return task;
    }

    public static TaskResource fromTask(Task task) {
        TaskResource resource = new TaskResource();
        resource.setId(task.getId());
        resource.setUrl(task.getUrl());
        resource.setName(task.getName());
        resource.setCount(task.getCount());
        resource.setStatsCompleted(task.getStatsCompleted());
        resource.setStatus(task.getStatus());
        resource.setTaskType(task.getTaskType());

        return resource;
    }
}
