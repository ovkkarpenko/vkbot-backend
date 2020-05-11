package com.sanyakarpenko.vkbot.services.impl;

import com.sanyakarpenko.vkbot.entities.Account;
import com.sanyakarpenko.vkbot.repositories.AccountRepository;
import com.sanyakarpenko.vkbot.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account saveAccount(Account account) {
        Optional<Account> accountOptional = accountRepository.findById(account.getId());

        if (accountOptional.isPresent()) {
            Account accountLoaded = accountOptional.get();

            account.setCreated(accountLoaded.getCreated());
            account.setUpdated(accountLoaded.getUpdated());
            account.setProgram(accountLoaded.getProgram());

            Account accountSaved = accountRepository.save(account);
            log.info("IN saveAccount - account: {} successfully saved", accountSaved);

            return accountSaved;
        }

        log.warn("IN saveAccount - no account found by id: {}", account.getId());
        return null;
    }

    @Override
    public Account addAccount(Account account) {
        Account addedAccount = accountRepository.save(account);
        log.warn("IN addAccount - account: {} successfully added", addedAccount);
        return null;
    }

    @Override
    public Account findAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);

        if(accountOptional.isPresent()) {
            Account account = accountOptional.get();
            log.info("IN findAccount - {} account found by id: {}", account, id);
            return account;
        }

        log.warn("IN findAccount - no account found by id: {}", id);
        return null;
    }
}
