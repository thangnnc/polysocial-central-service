package com.polysocial.dto;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListPostDTO implements Serializable{

    private int page;
    
    private int totalItem;

    private int totalPage;

    private List<PostResponseDTO> listPostDTO;
    
}
