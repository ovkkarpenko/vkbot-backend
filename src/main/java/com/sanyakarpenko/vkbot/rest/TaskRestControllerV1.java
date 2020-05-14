package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.resources.TaskRequestResource;
import com.sanyakarpenko.vkbot.resources.TaskResource;
import com.sanyakarpenko.vkbot.services.TaskService;
import com.sanyakarpenko.vkbot.types.TaskStatus;
import com.sanyakarpenko.vkbot.utils.Helper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/task",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestControllerV1 {
    private final TaskService taskService;

    public TaskRestControllerV1(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getTasks() {
        List<Task> tasks = taskService.findTasksByCurrentUser();
        return ResponseEntity.ok(tasks.stream().map(TaskResource::fromTask));
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskRequestResource requestResource) {
        Task task = requestResource.toTask();

        if(requestResource.getRunTaskAfterCreation()) {
            task.setStatus(TaskStatus.ACTIVE);
        }

        Task addedTask = taskService.addTask(task);
        return ResponseEntity.ok(TaskResource.fromTask(addedTask));
    }

    @PutMapping("/start/{taskId}")
    public ResponseEntity<?> startTask(@PathVariable Long taskId) {
        Task task = taskService.findTaskById(taskId);

        if (task != null && task.getUser().getUsername().equals(Helper.getUsername())) {
            task.setStatus(TaskStatus.ACTIVE);
            taskService.saveTask(task);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/stop/{taskId}")
    public ResponseEntity<?> stopTask(@PathVariable Long taskId) {
        Task task = taskService.findTaskById(taskId);

        if (task != null && task.getUser().getUsername().equals(Helper.getUsername())) {
            task.setStatus(TaskStatus.STOPPED);
            taskService.saveTask(task);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        Task task = taskService.findTaskById(taskId);

        if (task != null && task.getUser().getUsername().equals(Helper.getUsername())) {
            task.setStatus(TaskStatus.DELETED);
            taskService.saveTask(task);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
