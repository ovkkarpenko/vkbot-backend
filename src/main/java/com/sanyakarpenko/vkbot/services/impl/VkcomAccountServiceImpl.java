package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.entities.Settings;
import com.sanyakarpenko.vkbot.entities.Task;
import com.sanyakarpenko.vkbot.entities.VkcomAccount;
import com.sanyakarpenko.vkbot.repositories.SettingsRepository;
import com.sanyakarpenko.vkbot.repositories.VkcomAccountRepository;
import com.sanyakarpenko.vkbot.services.SettingsService;
import com.sanyakarpenko.vkbot.services.VkcomAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VkcomAccountServiceImpl implements VkcomAccountService {
    private VkcomAccountRepository vkcomAccountRepository;

    public VkcomAccountServiceImpl(VkcomAccountRepository vkcomAccountRepository) {
        this.vkcomAccountRepository = vkcomAccountRepository;
    }

    @Override
    public void addAccounts(List<VkcomAccount> accounts) {
        for (VkcomAccount account : accounts) {
            vkcomAccountRepository.save(account);
        }

        log.info("IN addAccounts - accounts : {} successfully added", accounts.size());
    }

    @Override
    public VkcomAccount update(VkcomAccount account) {
        VkcomAccount accountUpdated = vkcomAccountRepository.save(account);
        log.info("IN update - account : {} successfully updated", accountUpdated);

        return accountUpdated;
    }
}
