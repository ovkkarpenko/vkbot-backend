package com.sanyakarpenko.vkbot.repositories;

import com.sanyakarpenko.vkbot.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
