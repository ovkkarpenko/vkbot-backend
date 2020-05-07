package com.sanyakarpenko.vkbot.repositories;

import com.sanyakarpenko.vkbot.entities.VkcomAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VkcomAccountRepository extends JpaRepository<VkcomAccount, Long> {
    List<VkcomAccount> findAllByProgramBindingKey(String bindingKey);
}
