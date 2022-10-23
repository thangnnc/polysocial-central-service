package com.polysocial.entity;

import com.polysocial.entity.id.LikeId;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@IdClass(LikeId.class)
public class Likes implements Serializable {

    @Id
    private Long userId;

    @Id
    private Long postId;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private Posts post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Likes likes = (Likes) o;
        return userId != null && Objects.equals(userId, likes.userId)
                && postId != null && Objects.equals(postId, likes.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }
}
