package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.entities.Account;

import java.util.List;

public interface ProgramService {
    Program saveProgram(Program program);

    Program addProgram(Program program);

    List<Program> findProgramsByUsername(String username);

    List<Program> findProgramsByCurrentUser();

    Program findProgramByBindingKey(String bindingKey);

    Program findProgramById(Long id);

    List<Account> findProgramAccountsByBindingKey(String bindingKey);
}
