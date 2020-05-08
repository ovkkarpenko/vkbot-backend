package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.Task;

import java.util.List;

public interface TaskService {
    Task findTask(Long id);

    List<Task> findTasksByUsername(String username);

    List<Task> findCurrentUserTasks();

    Task addTask(Task task);

    void saveTask(Task task);
}
