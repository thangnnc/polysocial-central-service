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
public class Messages implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String content;

    private Boolean status;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contactId", insertable = false, updatable = false)
    private Contacts contact;

    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<MessageFile> messageFiles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Messages messages = (Messages) o;
        return messageId != null && Objects.equals(messageId, messages.messageId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
