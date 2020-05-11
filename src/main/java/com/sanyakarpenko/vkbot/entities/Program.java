package com.sanyakarpenko.vkbot.entities;

import com.sanyakarpenko.vkbot.types.ProgramStatus;
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
@ToString(exclude = {"accounts", "user"})
@NoArgsConstructor
public class Program extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "binding_key", nullable = false)
    private String bindingKey;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProgramStatus status;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "program")
    private List<Account> accounts;
}
