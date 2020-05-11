package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Program;
import lombok.Data;

@Data
public class ProgramRequestResource {
    private String name;

    public Program toProgram() {
        Program program = new Program();
        program.setName(name);

        return program;
    }

    public static ProgramRequestResource fromProgram(Program program) {
        ProgramRequestResource resource = new ProgramRequestResource();
        resource.setName(program.getName());

        return resource;
    }
}
