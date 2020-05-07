package com.sanyakarpenko.vkbot.entities;

import com.sanyakarpenko.vkbot.types.TaskStatus;
import com.sanyakarpenko.vkbot.types.VkcomUrlType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tasks")
@Data
@ToString(exclude = {"user"})
@NoArgsConstructor
public class Task extends BaseEntity {
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "count")
    private Integer count;

    @Column(name = "stats_done")
    private Integer statsDone;

    @Column(name = "stats_initial")
    private Integer statsInitial;

    @Column(name = "stats_current")
    private Integer statsCurrent;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "type_url")
    @Enumerated(EnumType.STRING)
    private VkcomUrlType typeUrl;

    @ManyToOne
    private User user;
}
