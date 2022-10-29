package com.polysocial.rest.controller.group;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.GroupDTO;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.entity.Groups;
import com.polysocial.service.impl.group.GroupServiceImpl;

@RestController
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;
    
    @GetMapping("/group/get/all")
    public ResponseEntity getAllPost(@RequestParam("page") Optional<Integer> page,
            @RequestParam("limit") Optional<Integer> limit) {
        List<GroupDTO> response = groupService.getAll(page.orElse(0),limit.orElse(3));
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
