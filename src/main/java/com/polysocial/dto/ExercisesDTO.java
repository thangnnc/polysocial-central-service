package com.polysocial.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExercisesDTO {
    private Long exId;

    private String content;

    private LocalDateTime endDate;
    
    private String deadline;

    private Boolean status = true;

    private Long groupId;

    public void formatEndDate() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	this.endDate = LocalDateTime.parse(deadline, formatter);
    	
    }
//    public void convertToLocalDateTimeViaInstant() {
//         deadline.toInstant()
//          .atZone(ZoneId.systemDefault())
//          .toLocalDateTime();
//         System.out.println(deadline);
//    }
}
