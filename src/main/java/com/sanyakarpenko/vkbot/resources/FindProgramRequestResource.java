package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Program;
import lombok.Data;

@Data
public class FindProgramRequestResource {
    private String bindingKey;

    public Program toProgram() {
        Program program = new Program();
        program.setBindingKey(bindingKey);

        return program;
    }
}
