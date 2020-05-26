package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.*;
import com.sanyakarpenko.vkbot.resources.*;
import com.sanyakarpenko.vkbot.services.*;
import com.sanyakarpenko.vkbot.types.TaskStatus;
import com.sanyakarpenko.vkbot.types.TaskType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/client_program",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ClientProgramRestControllerV1 {
    private final TaskService taskService;
    private final AccountService accountService;
    private final ProgramService programService;
    private final LogsService logsService;

    public ClientProgramRestControllerV1(TaskService taskService,
                                         AccountService accountService,
                                         ProgramService programService,
                                         LogsService logsService) {
        this.taskService = taskService;
        this.accountService = accountService;
        this.programService = programService;
        this.logsService = logsService;
    }

    @GetMapping(value = "/{bindingKey}",
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getProgram(@PathVariable String bindingKey) {
        Program program = programService.findProgramByBindingKey(bindingKey);

        if (program == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(3L, "No program found by bindingKey: " + bindingKey));
        }

        return ResponseEntity.ok(ProgramResource.fromProgram(program));
    }

    @GetMapping(value = "/account/{bindingKey}",
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> findProgramAccounts(@PathVariable String bindingKey) {
        Program program = programService.findProgramByBindingKey(bindingKey);
        if (program == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(3L, "No program found by bindingKey: " + bindingKey));
        }

        List<Account> accounts = programService.findProgramAccountsByBindingKey(bindingKey);
        return ResponseEntity.ok(accounts.stream().map(AccountResource::fromAccount));
    }

    /**
     * Save account information after authorisation via client program
     *
     * @param requestResource
     */
    @PutMapping("/account/{bindingKey}")
    public ResponseEntity<?> saveAccount(@PathVariable String bindingKey, @RequestBody AccountResource requestResource) {
        Program program = programService.findProgramByBindingKey(bindingKey);
        if (program == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(3L, "No program found by bindingKey: " + bindingKey));
        }

        Account account = accountService.findAccountById(requestResource.getId());
        if (account == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(4L, "No account found by accountId: " + requestResource.getId()));
        }

        accountService.saveAccount(requestResource.toAccount());
        return ResponseEntity.ok().build();
    }

    /**
     * Find tasks that have not been used before for the selected account
     *
     * @param bindingKey, accountId//
     */
    @GetMapping("/task/{accountId}/{bindingKey}")
    public ResponseEntity<?> findTasks(@PathVariable String bindingKey, @PathVariable Long accountId) {
        Program program = programService.findProgramByBindingKey(bindingKey);
        if (program == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(2L, "Invalid bindingKey"));
        }

        Account account = accountService.findAccountById(accountId);
        if (account == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(2L, "Invalid accountId"));
        }

        List<Task> tasks = program.getUser().getTasks();

        List<Task> filteredTasks = tasks
                .stream()
                .filter(task -> task.getStatus().equals(TaskStatus.ACTIVE) &&
                        !task.getAccountsHistory().contains(account))
                .collect(Collectors.toList());

        return ResponseEntity.ok(filteredTasks.stream().map(TaskResource::fromTask));
    }

    @PostMapping("/logs/{bindingKey}")
    public ResponseEntity<?> addLogs(@PathVariable String bindingKey, @RequestBody LogsResource requestResource) {
        Program program = programService.findProgramByBindingKey(bindingKey);
        if (program == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(2L, "Invalid bindingKey"));
        }

        Account account = accountService.findAccountById(requestResource.getAccountId());
        if (account == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(2L, "Invalid accountId"));
        }

        Logs logs = new Logs();
        logs.setProgram(program);
        logs.setAccount(account);
        logs.setMessage(requestResource.getMessage());

        logsService.addLog(logs);
        return ResponseEntity.ok().build();
    }

    /**
     * After completing a task mark the task as completed for the account
     *
     * @param requestResource
     */
    @PutMapping("/task/completed/{bindingKey}")
    public ResponseEntity<?> markTaskCompleted(@PathVariable String bindingKey, @RequestBody MarkTaskCompletedRequestResource requestResource) {
        Program program = programService.findProgramByBindingKey(bindingKey);
        if (program == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(2L, "Invalid bindingKey"));
        }

        Task task = taskService.findTaskById(requestResource.getTaskId());
        if (task == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(7L, "No task found by taskId: +" + requestResource.getTaskId()));
        }

        Account account = accountService.findAccountById(requestResource.getAccountId());
        if (account == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(4L, "No account found by accountId: +" + requestResource.getAccountId()));
        }

        task.increaseStatsCompleted();
        task.getAccountsHistory().add(account);

        taskService.saveTask(task);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/settings/{bindingKey}",
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getSettings(@PathVariable String bindingKey) {
        Program program = programService.findProgramByBindingKey(bindingKey);
        if (program == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(3L, "No program found by bindingKey: " + bindingKey));
        }

        Settings settings = program.getUser().getSettings();
        return ResponseEntity.ok(SettingsResource.fromSettings(settings));
    }
}
