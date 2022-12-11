package com.polysocial.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExercisesDetailDTO {
    
    private Long exId;

    private String content;

    private LocalDateTime endDate;
    
    private String deadline;

    private Boolean status = true;

    private Long groupId;

    private String url;

    private Boolean isSubmit;


    public void formatEndDate() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	this.endDate = LocalDateTime.parse(deadline, formatter);
    	
    }
    
}
