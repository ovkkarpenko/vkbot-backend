package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.Task;

import java.util.List;

public interface TaskService {
    List<Task> findTasks();

    Task addTask(Task task);

    void saveTask(Task task);
}
