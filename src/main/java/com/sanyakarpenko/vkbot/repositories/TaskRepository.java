package com.sanyakarpenko.vkbot.repositories;

import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserUsername(String username);
}
