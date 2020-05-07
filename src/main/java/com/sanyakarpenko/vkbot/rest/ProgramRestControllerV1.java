package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.Account;
import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.resources.*;
import com.sanyakarpenko.vkbot.services.ProgramService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/program",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ProgramRestControllerV1 {
    private final ProgramService programService;

    public ProgramRestControllerV1(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping
    public ResponseEntity<?> addProgram(@RequestBody AddProgramRequestResource requestResource) {
        Program program = requestResource.toProgram();
        Program createdProgram = programService.saveProgram(program);

        return ResponseEntity.ok(AddProgramResponseResource.fromProgram(createdProgram));
    }

    @GetMapping
    public ResponseEntity<?> findProgramsByUsername() {
        List<Program> programs = programService.findProgramsByUsername();
        return ResponseEntity.ok(programs.stream().map(ProgramResource::fromProgram));
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> findProgramAccountsByBindingKey(@RequestBody FindProgramAccountsRequestResource requestResource) {
        List<Account> accounts = programService.findProgramAccountsByBindingKey(requestResource.getBindingKey());
        return ResponseEntity.ok(accounts.stream().map(AccountResource::fromAccount));
    }
}
