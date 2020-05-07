package com.sanyakarpenko.vkbot.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "programs")
@Data
@ToString(exclude = {"accounts"})
@NoArgsConstructor
public class Program extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "binding_key", nullable = false)
    private String bindingKey;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "program")
    private List<Account> accounts;
}
