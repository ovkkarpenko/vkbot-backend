package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.Account;
import com.sanyakarpenko.vkbot.entities.BaseEntity;
import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.resources.AccountResource;
import com.sanyakarpenko.vkbot.resources.ProgramRequestResource;
import com.sanyakarpenko.vkbot.resources.ProgramResource;
import com.sanyakarpenko.vkbot.services.ProgramService;
import com.sanyakarpenko.vkbot.types.AccountStatus;
import com.sanyakarpenko.vkbot.types.ProgramStatus;
import com.sanyakarpenko.vkbot.utils.Helper;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/program",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ProgramRestControllerV1 {
    private final ProgramService programService;

    public ProgramRestControllerV1(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping(value = "/{programId}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getPrograms(@PathVariable Long programId) {
        Program program = programService.findProgramById(programId);

        if (program != null && program.getUser().getUsername().equals(Helper.getUsername())) {
            return ResponseEntity.ok(ProgramResource.fromProgram(program));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getPrograms() {
        List<Program> programs = programService.findProgramsByCurrentUser();
        return ResponseEntity.ok(programs
                .stream()
                .map(program -> {
                    ProgramResource resource = ProgramResource.fromProgram(program);
                    resource.setAccountCount(program.getAccounts().size());
                    resource.setValidAccounts((int) program.getAccounts()
                            .stream()
                            .filter(account -> account.getStatus() == AccountStatus.VALID).count());

                    return resource;
                }));
    }

    @GetMapping(value = "/{programId}/accounts", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getProgramAccounts(@PathVariable Long programId) {
        Program program = programService.findProgramById(programId);
        if(!program.getUser().getUsername().equals(Helper.getUsername())) {
            return ResponseEntity.badRequest().build();
        }

        List<Long> accounts = program.getAccounts().stream().map(BaseEntity::getId).collect(Collectors.toList());
        return ResponseEntity.ok(accounts);
    }

    @PostMapping
    public ResponseEntity<?> addProgram(@RequestBody ProgramRequestResource requestResource) {
        Program addedProgram = programService.addProgram(requestResource.toProgram());
        return ResponseEntity.ok(ProgramResource.fromProgram(addedProgram));
    }

    @PutMapping("/start/{programId}")
    public ResponseEntity<?> startProgram(@PathVariable Long programId) {
        Program program = programService.findProgramById(programId);

        if (program != null && program.getUser().getUsername().equals(Helper.getUsername())) {
            program.setStatus(ProgramStatus.ACTIVE);
            programService.saveProgram(program);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/stop/{programId}")
    public ResponseEntity<?> stopProgram(@PathVariable Long programId) {
        Program program = programService.findProgramById(programId);

        if (program != null && program.getUser().getUsername().equals(Helper.getUsername())) {
            program.setStatus(ProgramStatus.STOPPED);
            programService.saveProgram(program);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/delete/{programId}")
    public ResponseEntity<?> deleteProgram(@PathVariable Long programId) {
        Program program = programService.findProgramById(programId);

        if (program != null && program.getUser().getUsername().equals(Helper.getUsername())) {
            program.setStatus(ProgramStatus.DELETED);
            programService.saveProgram(program);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
