package com.polysocial.rest.controller.group;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.GroupDTO;

import com.polysocial.dto.MemberDTO;
import com.polysocial.dto.MemberGroupDTO;
import com.polysocial.dto.PageObject;
import com.polysocial.dto.StudentDTO;
import com.polysocial.dto.UserDTO;
import com.polysocial.service.group.GroupService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private JwtTokenProvider jwt;

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

    @DeleteMapping(value = CentralAPI.DELETE_MEMBER_GROUP)
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

    @DeleteMapping(value = CentralAPI.API_DELETE_GROUP)
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
            GroupDTO groups = groupService.createGroup(group);
            return new ResponseEntity(groups, HttpStatus.OK);
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
            UserDTO response = groupService.getOneMemberInGroup(email, groupId);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_MEMBER_GROUP, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMemberGroup(@RequestParam("groupId") Long groupId) {
        List<Object> response = groupService.getMemberInGroup(groupId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = CentralAPI.API_CREATE_GROUP_EXCEL, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity createExcel(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam Long groupId)
            throws IOException {
        try {
            List<MemberDTO> group = groupService.createExcel(file, groupId);
            return new ResponseEntity(group, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_ALL_GROUP_BY_STUDENT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllGroupStudent(@RequestHeader("Authorization") String token) {
        try {
            List<MemberGroupDTO> response = groupService.getAllGroupByStudent(jwt.getIdFromJWT(token));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_ALL_GROUP_BY_TEACHER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllGroupTeacher(@RequestHeader("Authorization") String token) {
        try {
            List<MemberGroupDTO> response = groupService.getAllGroupByTeacher(jwt.getIdFromJWT(token));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_ALL_GROUP_BY_USER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllGroupByUser(@RequestHeader("Authorization") String token) {
        try {
            List<MemberGroupDTO> response = groupService.getAllGroupByUser(jwt.getIdFromJWT(token));
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    
}
