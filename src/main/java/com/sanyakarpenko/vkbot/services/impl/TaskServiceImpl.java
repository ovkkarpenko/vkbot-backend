package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.Utils.Helper;
import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.entities.User;
import com.sanyakarpenko.vkbot.repositories.TaskRepository;
import com.sanyakarpenko.vkbot.repositories.UserRepository;
import com.sanyakarpenko.vkbot.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findTasks() {
        List<Task> tasks = taskRepository.findAllByUserUsername(Helper.getUsername());
        log.info("IN findTasks - {} tasks found", tasks.size());
        return tasks;
    }

    @Override
    public Task addTask(Task task) {
        task.setUser(userRepository.findByUsername(Helper.getUsername()));

        Task createdTask = taskRepository.save(task);
        log.info("IN addTask - task : {} successfully added", createdTask);

        return createdTask;
    }

    @Override
    public void saveTask(Task task) {
        Task taskUpdated = taskRepository.save(task);
        log.info("IN saveTask - task : {} successfully saved", taskUpdated);
    }
}
