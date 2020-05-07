package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.resources.AddProgramRequestResource;
import com.sanyakarpenko.vkbot.resources.AddProgramResponseResource;
import com.sanyakarpenko.vkbot.resources.FindProgramRequestResource;
import com.sanyakarpenko.vkbot.services.ProgramService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Program createdProgram = programService.addProgram(program);

        return ResponseEntity.ok(AddProgramResponseResource.fromProgram(createdProgram));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

    }

    @GetMapping("/findByToken}")
    public ResponseEntity<?> findByBindingKey(@RequestBody FindProgramRequestResource requestResource) {

    }
}
