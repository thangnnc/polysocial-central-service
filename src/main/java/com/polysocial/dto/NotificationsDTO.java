package com.polysocial.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationsDTO implements Serializable{
    private Long notificationId;
    private String content;
    private Integer type;
    private Boolean status = true;
    private LocalDateTime createdDate = LocalDateTime.now();
    private Long user;

    public NotificationsDTO(String content, Integer type, Long user) {
        this.content = content;
        this.type = type;
        this.user = user;
    }


}
