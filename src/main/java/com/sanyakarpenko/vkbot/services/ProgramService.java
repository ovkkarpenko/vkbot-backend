package com.sanyakarpenko.vkbot.services;


import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.entities.Account;

import java.util.List;

public interface ProgramService {
    Program saveProgram(Program program);

    List<Program> findProgramsByUsername();

    Program findProgramByBindingKey(String bindingKey);

    List<Account> findProgramAccountsByBindingKey(String bindingKey);
}
