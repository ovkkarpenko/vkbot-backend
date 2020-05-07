package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Program;
import lombok.Data;

@Data
public class AddProgramResponseResource {
    private String bindingKey;

    public static AddProgramResponseResource fromProgram(Program program) {
        AddProgramResponseResource responseResource = new AddProgramResponseResource();
        responseResource.setBindingKey(program.getBindingKey());

        return responseResource;
    }
}
