package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.*;
import com.sanyakarpenko.vkbot.resources.*;
import com.sanyakarpenko.vkbot.services.AccountService;
import com.sanyakarpenko.vkbot.services.ProgramService;
import com.sanyakarpenko.vkbot.services.SettingsService;
import com.sanyakarpenko.vkbot.services.TaskService;
import com.sanyakarpenko.vkbot.types.TaskType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/program",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProgramRestControllerV1 {
    private final TaskService taskService;
    private final AccountService accountService;
    private final ProgramService programService;
    private final SettingsService settingsService;

    public ProgramRestControllerV1(TaskService taskService,
                                   AccountService accountService,
                                   ProgramService programService,
                                   SettingsService settingsService) {
        this.taskService = taskService;
        this.accountService = accountService;
        this.programService = programService;
        this.settingsService = settingsService;
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

        Account account = accountService.findAccount(requestResource.getId());
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
     * @param requestResource
     */
    @PostMapping("/task/{bindingKey}")
    public ResponseEntity<?> findTasks(@PathVariable String bindingKey, @RequestBody FindProgramTasksRequestResource requestResource) {
        Program program = programService.findProgramByBindingKey(bindingKey);
        if (program == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(2L, "Invalid bindingKey"));
        }

        Account account = accountService.findAccount(requestResource.getAccountId());
        if (account == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(2L, "Invalid accountId"));
        }

        List<Task> tasks = taskService.findTasksByUsername(program.getUser().getUsername());

        List<Task> filteredTasks = tasks
                .stream()
                .filter(task -> !task.getAccountsHistory().contains(account) && task.getTaskType() == requestResource.getTaskType())
                .collect(Collectors.toList());

        return ResponseEntity.ok(filteredTasks.stream().map(TaskResource::fromTask));
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

        Task task = taskService.findTask(requestResource.getTaskId());
        if (task == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(7L, "No task found by taskId: +" + requestResource.getTaskId()));
        }

        Account account = accountService.findAccount(requestResource.getAccountId());
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

        Settings settings = settingsService.findSettingsByUsername(program.getUser().getUsername());
        return ResponseEntity.ok(SettingsResource.fromSettings(settings));
    }
}
