package com.sanyakarpenko.vkbot.resources;

import com.sanyakarpenko.vkbot.entities.Account;
import com.sanyakarpenko.vkbot.types.AccountStatus;
import com.sanyakarpenko.vkbot.types.Gender;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class AccountResource {
    private String token;
    private String fullName;
    private String country;
    private Date birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    public Account toAccount() {
        Account account = new Account();
        account.setToken(token);
        account.setFullName(fullName);
        account.setCountry(country);
        account.setBirthday(birthday);
        account.setGender(gender);
        account.setStatus(status);

        return account;
    }

    public static AccountResource fromAccount(Account account) {
        AccountResource resource = new AccountResource();
        resource.setToken(account.getToken());
        resource.setFullName(account.getFullName());
        resource.setCountry(account.getCountry());
        resource.setBirthday(account.getBirthday());
        resource.setGender(account.getGender());
        resource.setStatus(account.getStatus());

        return resource;
    }
}
