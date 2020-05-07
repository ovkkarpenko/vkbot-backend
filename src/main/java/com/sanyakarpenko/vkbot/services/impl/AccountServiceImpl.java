package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.entities.Account;
import com.sanyakarpenko.vkbot.entities.Program;
import com.sanyakarpenko.vkbot.repositories.AccountRepository;
import com.sanyakarpenko.vkbot.repositories.ProgramRepository;
import com.sanyakarpenko.vkbot.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccounts(List<Account> accounts) {
        for (Account account : accounts) {
            accountRepository.save(account);
        }

        log.info("IN addAccounts - accounts : {} successfully added", accounts.size());
    }

    @Override
    public Account saveAccount(Account account) {
        Account accountUpdated = accountRepository.save(account);
        log.info("IN saveAccount - account : {} successfully saved", accountUpdated);

        return accountUpdated;
    }
}
