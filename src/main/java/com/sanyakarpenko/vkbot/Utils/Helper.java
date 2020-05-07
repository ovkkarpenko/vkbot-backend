package com.sanyakarpenko.vkbot.Utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

public class Helper {
    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String generateProgramToken() {
        return RandomStringUtils.random(100, true, true);
    }
}
