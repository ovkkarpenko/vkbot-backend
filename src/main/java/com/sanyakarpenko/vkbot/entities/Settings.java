package com.sanyakarpenko.vkbot.entities;

import com.sanyakarpenko.vkbot.types.ProxyType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "settings")
@Data
@ToString(exclude = "user")
@NoArgsConstructor
public class Settings extends BaseEntity {
    @Column(name = "proxies")
    private String proxies;

    @Column(name = "user_agents")
    private String useragents;

    @Column(name = "rucaptcha_key")
    private String rucaptchaKey;

    @Column(name = "timeout_like")
    private Integer timeoutLike;

    @Column(name = "timeout_friend")
    private Integer timeoutFriend;

    @Column(name = "timeout_Repost")
    private Integer timeoutRepost;

    @Column(name = "timeout_group")
    private Integer timeoutGroup;

    @Column(name = "timeout_after_task")
    private Integer timeoutAfterTask;

    @Column(name = "proxy_type")
    @Enumerated(EnumType.STRING)
    private ProxyType proxyType;

    @OneToOne
    private User user;
}
