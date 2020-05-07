package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.entities.Settings;
import com.sanyakarpenko.vkbot.repositories.SettingsRepository;
import com.sanyakarpenko.vkbot.repositories.UserRepository;
import com.sanyakarpenko.vkbot.services.SettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SettingsServiceImpl implements SettingsService {
    private final SettingsRepository settingsRepository;

    public SettingsServiceImpl(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @Override
    public Settings saveSettings(Settings settings) {
        Settings savedSettings = settingsRepository.save(settings);
        log.info("IN saveSettings - settings : {} successfully saved", savedSettings);

        return savedSettings;
    }
}
