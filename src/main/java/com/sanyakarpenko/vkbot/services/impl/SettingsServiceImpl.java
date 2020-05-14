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
    private final SettingsRepository settingsRepository;

    public SettingsServiceImpl(SettingsRepository settingsRepository) {
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
        Settings loadedSettings = settingsRepository.findByUserUsername(Helper.getUsername());

        loadedSettings.setProxies(settings.getProxies());
        loadedSettings.setUseragents(settings.getUseragents());
        loadedSettings.setRucaptchaKey(settings.getRucaptchaKey());
        loadedSettings.setProxyType(settings.getProxyType());
        loadedSettings.setTimeoutLike(settings.getTimeoutLike());
        loadedSettings.setTimeoutRepost(settings.getTimeoutRepost());
        loadedSettings.setTimeoutFriend(settings.getTimeoutFriend());
        loadedSettings.setTimeoutGroup(settings.getTimeoutGroup());
        loadedSettings.setTimeoutAfterTask(settings.getTimeoutAfterTask());

        Settings savedSettings = settingsRepository.save(loadedSettings);
        log.info("IN saveSettings - settings : {} successfully saved", savedSettings);

        return savedSettings;
    }
}
