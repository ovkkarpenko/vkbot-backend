package com.sanyakarpenko.vkbot.services;

import com.sanyakarpenko.vkbot.entities.Account;

import java.util.List;

public interface AccountService {
    void addAccounts(List<Account> accounts);

    Account saveAccount(Account account);

    Account findAccount(Long accountId);
}
