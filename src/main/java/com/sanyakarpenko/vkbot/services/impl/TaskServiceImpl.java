package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.Utils.Helper;
import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.entities.User;
import com.sanyakarpenko.vkbot.entities.VkcomAccount;
import com.sanyakarpenko.vkbot.repositories.TaskRepository;
import com.sanyakarpenko.vkbot.repositories.UserRepository;
import com.sanyakarpenko.vkbot.repositories.VkcomAccountRepository;
import com.sanyakarpenko.vkbot.services.ProgramService;
import com.sanyakarpenko.vkbot.services.TaskService;
import com.sanyakarpenko.vkbot.types.TaskStatus;
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
    public List<Task> findAll() {
        List<Task> tasks = taskRepository.findAllByUserUsername(Helper.getUsername());
        log.info("IN findAll - {} tasks found", tasks.size());
        return tasks;
    }

    @Override
    public Task add(Task task) {
        User user = userRepository.findByUsername(Helper.getUsername());

        task.setUser(user);

        Task createdTask = taskRepository.save(task);
        log.info("IN add - task : {} successfully added", createdTask);

        return createdTask;
    }

    @Override
    public void update(Task task) {
        Task taskUpdated = taskRepository.save(task);
        log.info("IN update - task : {} successfully updated", taskUpdated);
    }
}
