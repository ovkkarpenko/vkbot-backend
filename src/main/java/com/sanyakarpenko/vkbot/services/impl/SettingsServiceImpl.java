package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.entities.Settings;
import com.sanyakarpenko.vkbot.entities.User;
import com.sanyakarpenko.vkbot.repositories.SettingsRepository;
import com.sanyakarpenko.vkbot.repositories.UserRepository;
import com.sanyakarpenko.vkbot.services.SettingsService;
import com.sanyakarpenko.vkbot.utils.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SettingsServiceImpl implements SettingsService {
    private final UserRepository userRepository;
    private final SettingsRepository settingsRepository;

    public SettingsServiceImpl(UserRepository userRepository, SettingsRepository settingsRepository) {
        this.userRepository = userRepository;
        this.settingsRepository = settingsRepository;
    }

    @Override
    public Settings findSettingsByCurrentUser() {
        Settings settings = settingsRepository.findByUserUsername(Helper.getUsername());
        log.info("IN findSettingsByCurrentUser - {} settings found", settings);
        return settings;
    }

    @Override
    public Settings saveSettings(Settings settings) {
        User user = userRepository.findByUsername(Helper.getUsername());

        settings.setUser(user);

        Settings savedSettings = settingsRepository.save(settings);
        log.info("IN saveSettings - settings : {} successfully saved", savedSettings);

        return savedSettings;
    }
}
