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
public class MessageFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messFileId;

    private String url;

    private String type;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "messageId", insertable = false, updatable = false)
    private Messages message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MessageFile that = (MessageFile) o;
        return messFileId != null && Objects.equals(messFileId, that.messFileId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
