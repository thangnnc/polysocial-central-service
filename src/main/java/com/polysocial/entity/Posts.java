package com.polysocial.entity;

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
public class Posts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String content;

    private LocalDateTime createdDate;
    
    private Long createdBy;

    @ManyToOne
    @JoinColumn(name = "createdBy", insertable = false, updatable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "groupId", insertable = false, updatable = false)
    private Groups group;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<PostFile> posts;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Likes> likes;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Comments> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Posts posts = (Posts) o;
        return postId != null && Objects.equals(postId, posts.postId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
