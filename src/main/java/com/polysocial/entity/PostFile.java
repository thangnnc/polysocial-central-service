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
public class PostFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postFileId;

    private String url;

    private String type;

    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private Posts post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostFile postFile = (PostFile) o;
        return postFileId != null && Objects.equals(postFileId, postFile.postFileId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
