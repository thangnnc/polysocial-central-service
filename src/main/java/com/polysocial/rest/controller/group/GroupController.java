package com.polysocial.rest.controller.group;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.internal.util.Members;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.GroupDTO;

import com.polysocial.dto.MemberDTO;
import com.polysocial.dto.PageObject;
import com.polysocial.dto.StudentDTO;
import com.polysocial.entity.Groups;
import com.polysocial.entity.Users;
import com.polysocial.service.impl.group.GroupServiceImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;
    
    @GetMapping(value=CentralAPI.GET_ALL_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity getAllGroup(@RequestParam("page") Optional<Integer> page,
            @RequestParam("limit") Optional<Integer> limit) {
        PageObject<GroupDTO> response = groupService.getAll(page.orElse(0),limit.orElse(3));
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value=CentralAPI.GET_ALL_GROUP_FALSE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity getAllGroupFalse(@RequestParam("page") Optional<Integer> page,
            @RequestParam("limit") Optional<Integer> limit) {
        PageObject<GroupDTO> response = groupService.getAllGroupFalse(page.orElse(0),limit.orElse(3));
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @GetMapping(value = CentralAPI.GET_ONE_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOneGroup(@RequestParam("groupId") Long groupId) {
        GroupDTO response = groupService.getOne(groupId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
 
    @DeleteMapping(value = CentralAPI.DELETE_MEMBER_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeStudentGroup(@RequestBody StudentDTO student) {
        groupService.deleteMemberToGroup(student);
        return new ResponseEntity("Deleted", HttpStatus.OK);
    }
    

    @DeleteMapping(value = CentralAPI.API_DELETE_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeGroup(@RequestBody GroupDTO group) {
        GroupDTO groups = groupService.deleteGroup(group);
        return new ResponseEntity(groups, HttpStatus.OK);
    }
    
    @GetMapping(value = CentralAPI.API_GET_TEACHER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTeacherGroup(@RequestParam("groupId") Long groupId) {
        Object response = groupService.getTeacherFromGroup(groupId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
   
    @PostMapping(value = CentralAPI.API_CREATE_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createGroup(@RequestBody GroupDTO group) {
        GroupDTO response = groupService.createGroup(group);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @PostMapping(value = CentralAPI.API_CREATE_MEMBER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveMember(@RequestBody StudentDTO student) {
        MemberDTO response = groupService.saveMember(student);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    
    @GetMapping(value = CentralAPI.API_GET_ONE_STUDENT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOneStudent(@RequestParam("email") String email, @RequestParam("userId") Long groupId) {
        Users response = groupService.getOneMemberInGroup(email,groupId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @GetMapping(value = CentralAPI.API_GET_MEMBER_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMemberGroup( @RequestParam("groupId") Long groupId) {
        List<Object> response = groupService.getMemberInGroup(groupId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @PostMapping(value = CentralAPI.API_CREATE_GROUP_EXCEL, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity createExcel(@RequestParam(value="file", required = false) MultipartFile file) throws IOException {
        Object group = groupService.createExcel(file);
        return new ResponseEntity(group,HttpStatus.OK);
    }
    
    @GetMapping(value = CentralAPI.API_FIND_GROUP_BY_KEYWORK, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findGroup(@RequestParam("keywork") String keywork) {
        List<GroupDTO> response = groupService.findByKeywork(keywork);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @PutMapping(value = CentralAPI.API_UPDATE_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateGroup(@RequestBody GroupDTO group) {
       try {
           GroupDTO response = groupService.updateGroup(group);
           return new ResponseEntity(response, HttpStatus.OK);
       }catch(Exception e) {
           return new ResponseEntity(e, HttpStatus.FAILED_DEPENDENCY);
       }
    }
    
    @GetMapping(value = CentralAPI.API_GET_ALL_GROUP_BY_STUDENT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllGroupStudent(@RequestParam("userId") Long userId) {
        List<MemberDTO> response = groupService.getAllGroupByStudent(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @GetMapping(value = CentralAPI.API_GET_ALL_GROUP_BY_TEACHER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllGroupTeacher(@RequestParam("userId") Long userId) {
        List<Object> response = groupService.getAllGroupByTeacher(userId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
