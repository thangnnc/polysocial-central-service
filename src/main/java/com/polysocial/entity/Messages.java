package com.polysocial.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "`Messages`")
public class Messages implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String content;

    private Boolean status = true;

    private Boolean statusCreated;

    private LocalDateTime createdDate = LocalDateTime.now();

    private Boolean messageRecall = true;

    public Messages( String content, Boolean statusCreated) {
        this.content = content;
        this.statusCreated = statusCreated;
    }


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "contactId")
    private Contacts contact;

    @JsonManagedReference
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
