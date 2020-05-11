package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.resources.ProgramRequestResource;
import com.sanyakarpenko.vkbot.resources.ProgramResource;
import com.sanyakarpenko.vkbot.services.ProgramService;
import com.sanyakarpenko.vkbot.types.ProgramStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<?> getPrograms() {
        List<Program> programs = programService.findProgramsByCurrentUser();
        return ResponseEntity.ok(programs.stream().map(ProgramResource::fromProgram));
    }

    @PostMapping
    public ResponseEntity<?> addProgram(@RequestBody ProgramRequestResource requestResource) {
        Program program = requestResource.toProgram();
        program.setStatus(ProgramStatus.STOPPED);

        Program addedProgram = programService.saveProgram(program);

        return ResponseEntity.ok(ProgramResource.fromProgram(addedProgram));
    }

    @PutMapping("/start/{programId}")
    public ResponseEntity<?> startProgram(@PathVariable Long programId) {
        Program program = programService.findProgramById(programId);

        if (program != null) {
            program.setStatus(ProgramStatus.ACTIVE);
            programService.saveProgram(program);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/stop/{programId}")
    public ResponseEntity<?> stopProgram(@PathVariable Long programId) {
        Program program = programService.findProgramById(programId);

        if (program != null) {
            program.setStatus(ProgramStatus.STOPPED);
            programService.saveProgram(program);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/delete/{programId}")
    public ResponseEntity<?> deleteProgram(@PathVariable Long programId) {
        Program program = programService.findProgramById(programId);

        if (program != null) {
            program.setStatus(ProgramStatus.DELETED);
            programService.saveProgram(program);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
