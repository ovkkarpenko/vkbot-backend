package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Program;
import lombok.Data;

@Data
public class ProgramResource {
    private String name;
    private String bindingKey;

    public Program toProgram() {
        Program program = new Program();
        program.setName(name);
        program.setBindingKey(bindingKey);

        return program;
    }

    public static ProgramResource fromProgram(Program program) {
        ProgramResource resource = new ProgramResource();
        resource.setName(program.getName());
        resource.setBindingKey(program.getBindingKey());

        return resource;
    }
}
