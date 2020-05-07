package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.entities.VkcomAccount;
import com.sanyakarpenko.vkbot.types.TaskStatus;

import java.util.List;

public interface TaskService {
    List<Task> findAll();

    Task add(Task task);

    void update(Task task);
}
