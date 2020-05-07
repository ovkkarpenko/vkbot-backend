package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.Utils.Helper;
import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.entities.User;
import com.sanyakarpenko.vkbot.entities.VkcomAccount;
import com.sanyakarpenko.vkbot.repositories.ProgramRepository;
import com.sanyakarpenko.vkbot.repositories.UserRepository;
import com.sanyakarpenko.vkbot.repositories.VkcomAccountRepository;
import com.sanyakarpenko.vkbot.services.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProgramServiceImpl implements ProgramService {
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;
    private final VkcomAccountRepository vkcomAccountRepository;

    public ProgramServiceImpl(UserRepository userRepository, ProgramRepository programRepository, VkcomAccountRepository vkcomAccountRepository) {
        this.userRepository = userRepository;
        this.programRepository = programRepository;
        this.vkcomAccountRepository = vkcomAccountRepository;
    }

    @Override
    public Program addProgram(Program program) {
        User user = userRepository.findByUsername(Helper.getUsername());

        program.setUser(user);
        program.setBindingKey(Helper.generateProgramToken());

        Program createdProgram = programRepository.save(program);
        log.info("IN addProgram - program : {} successfully added", program);

        return createdProgram;
    }

    @Override
    public List<Program> findAll() {
        List<Program> programs = programRepository.findAllByUserUsername(Helper.getUsername());
        log.info("IN findAll - {} programs found", programs.size());
        return programs;
    }

    @Override
    public Program findByBindingKey(String bindingKey) {
        Program program = programRepository.findByBindingKey(bindingKey);
        log.info("IN findByBindingKey - {} found by bindingKey: {}", program, bindingKey);
        return program;
    }

    @Override
    public List<VkcomAccount> findAllAccountsByBindingKey(String bindingKey) {
        List<VkcomAccount> accounts = vkcomAccountRepository.findAllByProgramBindingKey(bindingKey);
        log.info("IN findAllAccountsByBindingKey - {} accounts found", accounts.size());
        return accounts;
    }
}
