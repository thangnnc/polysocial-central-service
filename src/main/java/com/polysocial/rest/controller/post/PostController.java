package com.polysocial.rest.controller.post;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.PostDTO;
import com.polysocial.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(CentralAPI.GET_POST)
    public PostDTO getPost(){
        PostDTO response = postService.getPost();
        return response;
    }
}
