package com.polysocial.entity.id;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class MemberId implements Serializable {

    @Column
    private Long userId;

    @Column
    private Long groupId;

}
