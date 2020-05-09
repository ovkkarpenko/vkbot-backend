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
    private Long id;
    private String userId;
    private String token;
    private String fullName;
    private String country;
    private String userAgent;
    private String proxy;
    private Date birthday;
    private Gender gender;
    private AccountStatus status;

    public Account toAccount() {
        Account account = new Account();
        account.setId(id);
        account.setUserId(userId);
        account.setToken(token);
        account.setFullName(fullName);
        account.setCountry(country);
        account.setProxy(proxy);
        account.setUserAgent(userAgent);
        account.setBirthday(birthday);
        account.setGender(gender);
        account.setStatus(status);

        return account;
    }

    public static AccountResource fromAccount(Account account) {
        AccountResource resource = new AccountResource();
        resource.setId(account.getId());
        resource.setUserId(account.getUserId());
        resource.setToken(account.getToken());
        resource.setFullName(account.getFullName());
        resource.setCountry(account.getCountry());
        resource.setProxy(account.getProxy());
        resource.setUserAgent(account.getUserAgent());
        resource.setBirthday(account.getBirthday());
        resource.setGender(account.getGender());
        resource.setStatus(account.getStatus());

        return resource;
    }
}
