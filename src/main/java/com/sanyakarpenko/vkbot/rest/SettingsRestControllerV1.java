package com.sanyakarpenko.vkbot.rest;

import com.sanyakarpenko.vkbot.entities.Settings;
import com.sanyakarpenko.vkbot.resources.SettingsResource;
import com.sanyakarpenko.vkbot.services.SettingsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/settings",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class SettingsRestControllerV1 {
    private final SettingsService settingsService;

    public SettingsRestControllerV1(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping
    public ResponseEntity<?> getSettings() {
        Settings settings = settingsService.findSettingsByCurrentUser();
        return ResponseEntity.ok(SettingsResource.fromSettings(settings));
    }

    @PostMapping
    public ResponseEntity<?> saveSettings(@RequestBody SettingsResource requestResource) {
        Settings settings = requestResource.toSettings();

        Settings savedSettings = settingsService.saveSettings(settings);

        return ResponseEntity.ok(SettingsResource.fromSettings(savedSettings));
    }
}
