package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.VkcomAccount;

import java.util.List;

public interface VkcomAccountService {
    void addAccounts(List<VkcomAccount> accounts);

    VkcomAccount update(VkcomAccount account);
}
