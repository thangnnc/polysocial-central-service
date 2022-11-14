package com.polysocial.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseDTO implements Serializable {

    private Integer status;

    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus httpStatus){
        this.status = httpStatus.value();
        this.message = httpStatus.name();
    }
}