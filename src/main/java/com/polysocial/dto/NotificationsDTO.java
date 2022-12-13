package com.polysocial.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
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
