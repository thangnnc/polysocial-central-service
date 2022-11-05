package com.polysocial.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class TaskEx implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Float mark;

    @ManyToOne
    @JoinColumn(name = "exId", insertable = false, updatable = false)
    private Exercises exercise;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "userId", referencedColumnName="userId"),
            @JoinColumn(name = "groupId", referencedColumnName="groupId")
    })
    private Members member;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<TaskFile> taskFiles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TaskEx taskEx = (TaskEx) o;
        return taskId != null && Objects.equals(taskId, taskEx.taskId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
