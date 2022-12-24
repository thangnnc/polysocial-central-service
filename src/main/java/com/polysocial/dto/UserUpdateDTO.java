package com.polysocial.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO implements Serializable{
    private Long userId;
    private String fullName;
    private String avatar;
    private String email;
    private String birthdays;
    private LocalDateTime birthday;
    private Boolean gender;
    private String address;
    private String major;
    private String course;
    private String roleName; 
    
    public void formatEndDate() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	this.birthday = LocalDateTime.parse(birthdays, formatter);
    	
    }
  
}
