package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Account;
import com.sanyakarpenko.vkbot.types.AccountStatus;
import com.sanyakarpenko.vkbot.types.Gender;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AddAccountsRequestResource {
    private Long programId;
    private List<String> tokens;
}
