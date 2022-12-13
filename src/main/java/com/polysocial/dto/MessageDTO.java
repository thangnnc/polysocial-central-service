package com.polysocial.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO implements Serializable {


    private Long messageId;

    private String content;

    private Boolean status;

    private LocalDateTime createdDate;
    
    private Long contactId;
    
    private Long roomId;

    private Long listcontactId[];
    
    private Boolean messageRecall;
}
