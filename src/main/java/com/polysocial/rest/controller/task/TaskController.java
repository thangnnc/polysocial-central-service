package com.polysocial.rest.controller.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.polysocial.consts.CentralAPI;
import com.polysocial.service.task.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = CentralAPI.API_CREATE_TASK_FILE)
    public ResponseEntity createTask(@RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam Long exId, @RequestParam Long userId, @RequestParam Long groupId) {
        try {
            return ResponseEntity.ok().body(taskService.saveFile(file, exId, userId, groupId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    

}
