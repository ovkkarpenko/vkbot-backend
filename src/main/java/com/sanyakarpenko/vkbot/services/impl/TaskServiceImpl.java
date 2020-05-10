package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.utils.Helper;
import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.repositories.TaskRepository;
import com.sanyakarpenko.vkbot.repositories.UserRepository;
import com.sanyakarpenko.vkbot.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Task> findTasksByUsername(String username) {
        List<Task> tasks = taskRepository.findAllByUserUsername(username);
        log.info("IN findTasksByUsername - {} tasks found", tasks.size());
        return tasks;
    }

    @Override
    public Task findTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        if(taskOptional.isPresent()) {
            Task task = taskOptional.get();
            log.info("IN findTaskById - {} task found by id: {}", task, id);
            return task;
        }

        log.warn("IN findTaskById - no task found by id: {}", id);
        return null;
    }

    @Override
    public List<Task> findCurrentUserTasks() {
        List<Task> tasks = findTasksByUsername(Helper.getUsername());
        log.info("IN findCurrentUserTasks - {} tasks found", tasks.size());
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
