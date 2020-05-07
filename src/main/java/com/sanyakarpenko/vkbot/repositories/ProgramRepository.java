package com.sanyakarpenko.vkbot.repositories;

import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findAllByUserUsername(String username);

    Program findByBindingKey(String bindingKey);
}
