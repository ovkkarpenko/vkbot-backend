package com.sanyakarpenko.vkbot.entities;

import com.sanyakarpenko.vkbot.types.AccountStatus;
import com.sanyakarpenko.vkbot.types.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounts")
@Data
@ToString(exclude = {"token", "program", "tasks"})
@NoArgsConstructor
public class Account extends BaseEntity {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "token")
    private String token;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "country")
    private String country;

    @Column(name = "proxy")
    private String proxy;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NONE;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.NONE;

    @ManyToOne
    private Program program;

    @ManyToMany(mappedBy = "accountsHistory", fetch = FetchType.LAZY)
    private List<Task> tasks;
}
