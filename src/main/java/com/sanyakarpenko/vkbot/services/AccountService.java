package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.Account;

import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);

    Account addAccount(Account account);

    Account findAccountById(Long accountId);
}
