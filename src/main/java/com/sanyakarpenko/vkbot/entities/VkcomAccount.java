package com.sanyakarpenko.vkbot.entities;

import com.sanyakarpenko.vkbot.types.Gender;
import com.sanyakarpenko.vkbot.types.VkcomAccountStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vkcom_accounts")
@Data
@ToString(exclude = {"token", "program"})
@NoArgsConstructor
public class VkcomAccount extends BaseEntity {
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "country")
    private String country;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private VkcomAccountStatus status;

    @ManyToOne
    private Program program;
}
