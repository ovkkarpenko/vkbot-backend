package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.Account;
import com.sanyakarpenko.vkbot.entities.Logs;
import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.resources.AddAccountsRequestResource;
import com.sanyakarpenko.vkbot.resources.ErrorResponseResource;
import com.sanyakarpenko.vkbot.resources.ProgramResource;
import com.sanyakarpenko.vkbot.services.AccountService;
import com.sanyakarpenko.vkbot.services.ProgramService;
import com.sanyakarpenko.vkbot.types.AccountStatus;
import com.sanyakarpenko.vkbot.types.Gender;
import com.sanyakarpenko.vkbot.types.TaskStatus;
import com.sanyakarpenko.vkbot.utils.Helper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/account",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountRestControllerV1 {
    private final AccountService accountService;
    private final ProgramService programService;

    public AccountRestControllerV1(AccountService accountService, ProgramService programService) {
        this.accountService = accountService;
        this.programService = programService;
    }

    @PostMapping
    public ResponseEntity<?> addAccounts(@RequestBody AddAccountsRequestResource requestResource) {
        Program program = programService.findProgramById(requestResource.getProgramId());

        if (program == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponseResource(9L, "No program found by programId: " + requestResource.getProgramId()));
        }

        List<String> tokens = requestResource.getTokens();

        for (String token : tokens) {
            Account account = new Account();
            account.setToken(token);
            account.setStatus(AccountStatus.NONE);
            account.setGender(Gender.NONE);
            account.setProgram(program);

            accountService.addAccount(account);
        }

        return null;
    }

    @GetMapping(value = "/{accountId}/logs", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getAccountLogs(@PathVariable Long accountId) {
        Account account = accountService.findAccountById(accountId);
        if(!account.getProgram().getUser().getUsername().equals(Helper.getUsername())) {
            return ResponseEntity.badRequest().build();
        }

        List<String> logs = account.getLogs().stream().map(Logs::getMessage).collect(Collectors.toList());
        return ResponseEntity.ok(logs);
    }

    @PutMapping("/delete/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        Account account = accountService.findAccountById(accountId);

        if (account != null) {
            account.setStatus(AccountStatus.DELETED);
            accountService.saveAccount(account);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
