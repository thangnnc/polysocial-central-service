package com.polysocial.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.polysocial.entity.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StorageCapacityDTO implements Serializable{
    
    private Long id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Long userId;
    private String fullName;
    private Long capacity;
    private Long used;


    // public StorageCapacityDTO(Long id, Long userId, Long capacity, Long used) {
    //     this.id = id;
    //     this.userId = userId;
    //     this.capacity = capacity;
    //     this.used = used;
    // }

}
