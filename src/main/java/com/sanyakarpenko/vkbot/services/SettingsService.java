package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.Settings;
import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.types.TaskStatus;

public interface SettingsService {
    Settings findSettingsByCurrentUser();

    Settings findSettingsByUsername(String username);

    Settings saveSettings(Settings settings);
}
