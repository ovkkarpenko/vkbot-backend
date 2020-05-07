package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.Utils.Helper;
import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.entities.User;
import com.sanyakarpenko.vkbot.entities.Account;
import com.sanyakarpenko.vkbot.repositories.ProgramRepository;
import com.sanyakarpenko.vkbot.repositories.UserRepository;
import com.sanyakarpenko.vkbot.repositories.AccountRepository;
import com.sanyakarpenko.vkbot.services.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProgramServiceImpl implements ProgramService {
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;
    private final AccountRepository accountRepository;

    public ProgramServiceImpl(UserRepository userRepository, ProgramRepository programRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.programRepository = programRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Program saveProgram(Program program) {
        User user = userRepository.findByUsername(Helper.getUsername());

        program.setUser(user);
        program.setBindingKey(Helper.generateProgramToken());

        Program savedProgram = programRepository.save(program);
        log.info("IN saveProgram - program : {} successfully saved", savedProgram);

        return savedProgram;
    }

    @Override
    public List<Program> findProgramsByUsername() {
        List<Program> programs = programRepository.findAllByUserUsername(Helper.getUsername());
        log.info("IN findProgramsByUsername - {} programs found", programs.size());
        return programs;
    }

    @Override
    public Program findProgramByBindingKey(String bindingKey) {
        Program program = programRepository.findByBindingKey(bindingKey);
        log.info("IN findProgramByBindingKey - {} found by bindingKey: {}", program, bindingKey);
        return program;
    }

    @Override
    public List<Account> findProgramAccountsByBindingKey(String bindingKey) {
        List<Account> accounts = accountRepository.findAllByProgramBindingKey(bindingKey);
        log.info("IN findProgramAccountsByBindingKey - {} accounts found", accounts.size());
        return accounts;
    }
}
