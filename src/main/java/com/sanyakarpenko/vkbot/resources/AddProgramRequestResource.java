package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Program;
import lombok.Data;

@Data
public class AddProgramRequestResource {
    private String programName;

    public Program toProgram() {
        Program program = new Program();
        program.setName(programName);

        return program;
    }
}
