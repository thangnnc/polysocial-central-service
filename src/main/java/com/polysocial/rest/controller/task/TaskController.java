package com.polysocial.rest.controller.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.TaskExDTO;
import com.polysocial.dto.TaskFileCreateDTO;
import com.polysocial.dto.TaskFileDTO;
import com.polysocial.entity.TaskEx;
import com.polysocial.service.task.TaskService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private JwtTokenProvider jwt;

    @PostMapping(value = CentralAPI.API_CREATE_TASK_FILE, consumes = { "multipart/form-data" })
    public ResponseEntity createTask(@RequestParam(value = "file", required = false) MultipartFile file, @RequestHeader("Authorization") String token,
            @ModelAttribute TaskFileCreateDTO taskFile) {
        try {
            taskFile.setUserId(jwt.getIdFromJWT(token));
            return ResponseEntity.ok(taskService.saveFile(file, taskFile));
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping(value = CentralAPI.API_UPDATE_TASK_FILE, consumes = { "multipart/form-data;" })
    public ResponseEntity updatetaskFile(@RequestParam(value = "file", required = false) MultipartFile file,
            @ModelAttribute TaskFileCreateDTO taskFile, @RequestHeader("Authorization") String token) {
        try {
            taskFile.setUserId(jwt.getIdFromJWT(token));
            return ResponseEntity.ok(taskService.updateFile(file, taskFile));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_TASK_FILE_UPLOAD + "/{groupId}/{userId}/{exId}")
    public ResponseEntity getFileUpload(@PathVariable Long groupId, @PathVariable Long userId,
            @PathVariable Long exId) {
        try {
            return ResponseEntity.ok().body(taskService.getFileUploadGroup(exId, userId, groupId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(value = CentralAPI.API_DELETE_TASK_FILE)
    public ResponseEntity deleteTaskFile(@RequestBody TaskFileDTO taskFile) {
        try {
            taskService.deleteTaskFile(taskFile);
            return (ResponseEntity) ResponseEntity.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = CentralAPI.API_CREATE_MARK_TASK)
    public ResponseEntity createMark(@RequestBody TaskExDTO task) {
        try {
            return ResponseEntity.ok(taskService.createMark(task));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = CentralAPI.API_GET_ALL_TASK_FILE)
    public ResponseEntity getAllTaskFile(@RequestParam Long exId, @RequestParam Long groupId) {
        try {
            return ResponseEntity.ok(taskService.getAllTaskFile(exId, groupId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
