package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.types.ProgramStatus;
import lombok.Data;

@Data
public class ProgramResource {
    private Long id;
    private String name;
    private String bindingKey;
    private Integer accountCount;
    private Integer validAccounts;
    private ProgramStatus status;

    public Program toProgram() {
        Program program = new Program();
        program.setId(id);
        program.setName(name);
        program.setBindingKey(bindingKey);
        program.setStatus(status);

        return program;
    }

    public static ProgramResource fromProgram(Program program) {
        ProgramResource resource = new ProgramResource();
        resource.setId(program.getId());
        resource.setName(program.getName());
        resource.setBindingKey(program.getBindingKey());
        resource.setStatus(program.getStatus());

        return resource;
    }
}
