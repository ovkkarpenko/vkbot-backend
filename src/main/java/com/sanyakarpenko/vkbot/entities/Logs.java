package com.sanyakarpenko.vkbot.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "logs")
@Data
@ToString(exclude = {"account", "program"})
@NoArgsConstructor
public class Logs extends BaseEntity {
    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Program program;
}
