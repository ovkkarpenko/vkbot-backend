package com.sanyakarpenko.vkbot.repositories;

import com.sanyakarpenko.vkbot.entities.Logs;
import com.sanyakarpenko.vkbot.entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<Logs, Long> {
}
