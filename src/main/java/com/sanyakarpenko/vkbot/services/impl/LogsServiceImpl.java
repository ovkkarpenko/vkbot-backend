package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.entities.Logs;
import com.sanyakarpenko.vkbot.entities.Settings;
import com.sanyakarpenko.vkbot.repositories.LogsRepository;
import com.sanyakarpenko.vkbot.repositories.SettingsRepository;
import com.sanyakarpenko.vkbot.services.LogsService;
import com.sanyakarpenko.vkbot.utils.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class LogsServiceImpl implements LogsService {
    private final LogsRepository logsRepository;

    public LogsServiceImpl(LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }

    @Override
    public Logs addLog(Logs logs) {
        Logs addedLogs = logsRepository.save(logs);
        log.info("IN saveLog - logs: {} successfully added", logs);
        return addedLogs;
    }
}
