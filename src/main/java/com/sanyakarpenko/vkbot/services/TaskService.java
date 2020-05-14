package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.Task;

import java.util.List;

public interface TaskService {
    Task findTaskById(Long id);

    List<Task> findTasksByCurrentUser();

    List<Task> findTasksByUsername(String username);

    Task saveTask(Task task);

    Task addTask(Task task);
}
