package com.polysocial.service.impl.task;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.polysocial.consts.TaskAPI;
import com.polysocial.dto.TaskExDTO;
import com.polysocial.dto.TaskFileDTO;
import com.polysocial.entity.TaskEx;
import com.polysocial.entity.TaskFile;
import com.polysocial.service.task.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public TaskFileDTO createTaskFile(TaskFile taskFile) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteTaskFile(Long taskFileId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public TaskFile saveFile(MultipartFile file, Long exId, Long userId, Long groupId) {
        try {
            String url = TaskAPI.API_TASK_FILE_CREATE;
            Path tempFile = Files.createTempFile(null, null);

            Files.write(tempFile, file.getBytes());
            File fileToSend = tempFile.toFile();

            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();

            parameters.add("file", new FileSystemResource(fileToSend));
            parameters.add("exId", exId);
            parameters.add("userId", userId);
            parameters.add("groupId", groupId);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "multipart/form-data");

            HttpEntity httpEntity = new HttpEntity<>(parameters, headers);

            ResponseEntity<TaskFile> group = restTemplate.exchange(url, HttpMethod.POST,
                    httpEntity, TaskFile.class);
            return group.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaskFile updateFile(MultipartFile file, Long exId, Long userId, Long groupId) {
        try {
            String url = TaskAPI.API_TASK_FILE_UPDATE;
            Path tempFile = Files.createTempFile(null, null);

            Files.write(tempFile, file.getBytes());
            File fileToSend = tempFile.toFile();

            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();

            parameters.add("file", new FileSystemResource(fileToSend));
            parameters.add("exId", exId);
            parameters.add("userId", userId);
            parameters.add("groupId", groupId);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "multipart/form-data");

            HttpEntity httpEntity = new HttpEntity<>(parameters, headers);

            ResponseEntity<TaskFile> group = restTemplate.exchange(url, HttpMethod.PUT,
                    httpEntity, TaskFile.class);
            return group.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaskFile getFileUploadGroup(Long exId, Long userId, Long groupId) {
        try {
            String url = TaskAPI.API_GET_FILE_UPLOAD_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("exId", exId)
                    .queryParam("userId", userId)
                    .queryParam("groupId", groupId)
                    .build();
            ResponseEntity<TaskFile> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, null,
            TaskFile.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaskExDTO createTaskEx(TaskEx taskExDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TaskExDTO updateTaskEx(Long taskId, TaskEx taskExDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteTaskEx(Long taskId) {
        // TODO Auto-generated method stub
        
    }
    
}
