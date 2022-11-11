package com.polysocial.rest.controller.post;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.service.post.PostService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostService postService;
    
    @GetMapping(CentralAPI.GET_ALL_POST)
    public ResponseEntity getAllPost(@RequestParam("page") Optional<Integer> page,
            @RequestParam("limit") Optional<Integer> limit) {
        ListPostDTO response = postService.getAllPosts(page.orElse(0), limit.orElse(10));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
