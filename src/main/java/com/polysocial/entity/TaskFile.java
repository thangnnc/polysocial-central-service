package com.polysocial.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class TaskFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskFileId;

    private String url;

    private String type;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "taskId", insertable = false, updatable = false)
    private TaskEx task;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TaskFile taskFile = (TaskFile) o;
        return taskFileId != null && Objects.equals(taskFileId, taskFile.taskFileId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
