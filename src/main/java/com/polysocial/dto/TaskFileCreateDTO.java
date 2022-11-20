package com.polysocial.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskFileCreateDTO implements Serializable{
    private Long exId;
    private Long userId;
    private Long groupId;
    private String path;
}
