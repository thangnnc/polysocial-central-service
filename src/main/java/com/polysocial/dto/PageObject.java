package com.polysocial.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.polysocial.entity.Groups;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PageObject<T> {
   private List<T> content;
   private Object pageable;
   private Integer totalPages;
   private Integer totalElements;
   private Boolean last;
   private Boolean first;
   private Integer number;
   private Integer numberOfElements;
   private Integer size;
   
}
