package com.sanyakarpenko.vkbot.services;


import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.entities.VkcomAccount;

import java.util.List;

public interface ProgramService {
    Program addProgram(Program program);

    List<Program> findAll();

    Program findByBindingKey(String bindingKey);

    List<VkcomAccount> findAllAccountsByBindingKey(String bindingKey);
}
