package com.polysocial.rest.controller.group;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.internal.util.Members;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.polysocial.entity.Users;
import com.polysocial.service.impl.group.GroupServiceImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;

    @GetMapping(value = CentralAPI.GET_ALL_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllGroup(@RequestParam("page") Optional<Integer> page,
            @RequestParam("limit") Optional<Integer> limit) {
        try {
            PageObject<GroupDTO> response = groupService.getAll(page.orElse(0), limit.orElse(3));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.GET_ALL_GROUP_FALSE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllGroupFalse(@RequestParam("page") Optional<Integer> page,
            @RequestParam("limit") Optional<Integer> limit) {
        try {
            PageObject<GroupDTO> response = groupService.getAllGroupFalse(page.orElse(0), limit.orElse(3));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.GET_ONE_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOneGroup(@RequestParam("groupId") Long groupId) {
        try {
            GroupDTO response = groupService.getOne(groupId);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = CentralAPI.DELETE_MEMBER_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeStudentGroup(@RequestParam("groupId") Long groupId,
            @RequestParam("userId") Long userId) {
        try {
            groupService.deleteMemberToGroup(groupId, userId);
            return new ResponseEntity("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = CentralAPI.API_DELETE_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeGroup(@RequestParam Long groupId) {
        try {
            GroupDTO groups = groupService.deleteGroup(groupId);
            return new ResponseEntity(groups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_TEACHER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTeacherGroup(@RequestParam("groupId") Long groupId) {
        try {
            Object response = groupService.getTeacherFromGroup(groupId);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = CentralAPI.API_CREATE_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createGroup(@RequestBody GroupDTO group) {
        try {
            GroupDTO response = groupService.createGroup(group);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = CentralAPI.API_CREATE_MEMBER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveMember(@RequestBody StudentDTO student) {
        try {
            MemberDTO response = groupService.saveMember(student);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_ONE_STUDENT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getOneStudent(@RequestParam("email") String email, @RequestParam("userId") Long groupId) {
        try {
            Users response = groupService.getOneMemberInGroup(email, groupId);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_MEMBER_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMemberGroup(@RequestParam("groupId") Long groupId) {
        try {
            List<Object> response = groupService.getMemberInGroup(groupId);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = CentralAPI.API_CREATE_GROUP_EXCEL, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity createExcel(@RequestParam(value = "file", required = false) MultipartFile file)
            throws IOException {
        try {
            Object group = groupService.createExcel(file);
            return new ResponseEntity(group, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.API_FIND_GROUP_BY_KEYWORK, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findGroup(@RequestParam("keywork") String keywork) {
        try {
            List<GroupDTO> response = groupService.findByKeywork(keywork);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = CentralAPI.API_UPDATE_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateGroup(@RequestBody GroupDTO group) {
        try {
            GroupDTO response = groupService.updateGroup(group);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_ALL_GROUP_BY_STUDENT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllGroupStudent(@RequestParam("userId") Long userId) {
        try {
            List<MemberDTO> response = groupService.getAllGroupByStudent(userId);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_ALL_GROUP_BY_TEACHER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllGroupTeacher(@RequestParam("userId") Long userId) {
        try {
            List<Object> response = groupService.getAllGroupByTeacher(userId);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
