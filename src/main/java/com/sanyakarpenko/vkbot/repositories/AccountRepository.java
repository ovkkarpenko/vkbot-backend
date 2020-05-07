package com.sanyakarpenko.vkbot.repositories;

import com.sanyakarpenko.vkbot.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByProgramBindingKey(String bindingKey);
}
