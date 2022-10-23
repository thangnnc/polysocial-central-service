package com.polysocial.entity;

import com.polysocial.entity.id.MemberId;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@IdClass(MemberId.class)
public class Members implements Serializable {

    @Id
    private Long userId;

    @Id
    private Long groupId;

    private Boolean isTeacher;

    @OneToMany(mappedBy = "member")
    private List<TaskEx> taskExes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Members members = (Members) o;
        return userId != null && Objects.equals(userId, members.userId)
                && groupId != null && Objects.equals(groupId, members.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }
}
