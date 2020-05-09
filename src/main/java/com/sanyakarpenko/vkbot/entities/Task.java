package com.sanyakarpenko.vkbot.entities;

import com.sanyakarpenko.vkbot.types.TaskStatus;
import com.sanyakarpenko.vkbot.types.ObjectType;
import com.sanyakarpenko.vkbot.types.TaskType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tasks")
@Data
@ToString(exclude = {"user", "accountsHistory"})
@NoArgsConstructor
public class Task extends BaseEntity {
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "count")
    private Integer count;

    @Column(name = "stats_completed")
    private Integer statsCompleted;

    @Column(name = "stats_initial")
    private Integer statsInitial;

    @Column(name = "stats_current")
    private Integer statsCurrent;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "object_type")
    @Enumerated(EnumType.STRING)
    private ObjectType objectType;

    @Column(name = "task_type")
    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_histories")
    private List<Account> accountsHistory;

    public void increaseStatsCompleted() {
        statsCompleted += 1;
    }
}
