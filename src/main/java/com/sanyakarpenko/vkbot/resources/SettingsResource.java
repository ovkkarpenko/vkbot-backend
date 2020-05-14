package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Settings;
import com.sanyakarpenko.vkbot.types.ProxyType;
import lombok.Data;

@Data
public class SettingsResource {
    private String proxies;
    private String useragents;
    private String rucaptchaKey;
    private Integer timeoutLike;
    private Integer timeoutFriend;
    private Integer timeoutRepost;
    private Integer timeoutGroup;
    private Integer timeoutAfterTask;
    private ProxyType proxyType;

    public Settings toSettings() {
        Settings settings = new Settings();
        settings.setProxies(proxies);
        settings.setUseragents(useragents);
        settings.setRucaptchaKey(rucaptchaKey);
        settings.setTimeoutLike(timeoutLike);
        settings.setTimeoutFriend(timeoutFriend);
        settings.setTimeoutRepost(timeoutRepost);
        settings.setTimeoutGroup(timeoutGroup);
        settings.setTimeoutAfterTask(timeoutAfterTask);
        settings.setProxyType(proxyType);

        return settings;
    }

    public static SettingsResource fromSettings(Settings settings) {
        SettingsResource resource = new SettingsResource();
        resource.setProxies(settings.getProxies());
        resource.setUseragents(settings.getUseragents());
        resource.setRucaptchaKey(settings.getRucaptchaKey());
        resource.setTimeoutLike(settings.getTimeoutLike());
        resource.setTimeoutFriend(settings.getTimeoutFriend());
        resource.setTimeoutRepost(settings.getTimeoutRepost());
        resource.setTimeoutGroup(settings.getTimeoutGroup());
        resource.setTimeoutAfterTask(settings.getTimeoutAfterTask());
        resource.setTimeoutGroup(settings.getTimeoutGroup());
        resource.setProxyType(settings.getProxyType());

        return resource;
    }
}
