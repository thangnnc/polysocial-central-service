package com.polysocial.service.impl.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.cloudinary.Cloudinary;
import com.polysocial.consts.TaskAPI;
import com.polysocial.dto.NotificationsDTO;
import com.polysocial.dto.TaskDetailDTO;
import com.polysocial.dto.TaskExDTO;
import com.polysocial.dto.TaskExDetailDTO;
import com.polysocial.dto.TaskFileCreateDTO;
import com.polysocial.dto.TaskFileDTO;
import com.polysocial.entity.Members;
import com.polysocial.entity.TaskEx;
import com.polysocial.entity.TaskFile;
import com.polysocial.notification.ContentNotifications;
import com.polysocial.repo.GroupRepo;
import com.polysocial.repo.MemberRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.service.task.TaskService;
import com.polysocial.type.TypeNotifications;
import com.polysocial.utils.UploadToCloud;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UploadToCloud uploadToCloud;

    @Autowired
    private NotificationsService notificationsService;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public Object saveFile(MultipartFile file, TaskFileCreateDTO taskFile) {
        try {
            String url = TaskAPI.API_TASK_FILE_CREATE;
            String urlPath = uploadToCloud.saveFile(file);
            taskFile.setPath(urlPath);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity header = new HttpEntity(taskFile, headers);
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("TaskFileCreateDTO", taskFile)
                    .build();
            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, header,
                    Object.class);

            Members member = memberRepo.getTeacherByMember(taskFile.getGroupId());
            String fullName = userRepo.findById(taskFile.getUserId()).get().getFullName();
            String groupName = groupRepo.findById(taskFile.getGroupId()).get().getName();
            NotificationsDTO noti = new NotificationsDTO(
                    String.format(ContentNotifications.NOTI_CONTENT_UPLOAD_FILE_GROUP, fullName, groupName),
                    TypeNotifications.NOTI_TYPE_UPLOAD_FILE_GROUP, member.getUserId());
            notificationsService.createNoti(noti);

            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object updateFile(MultipartFile file, TaskFileCreateDTO taskFile) {
        try {
            String url = TaskAPI.API_TASK_FILE_UPDATE;
            String urlPath = uploadToCloud.saveFile(file);
            taskFile.setPath(urlPath);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity(taskFile, headers);
            ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                    entity, Object.class);

            Members member = memberRepo.getTeacherByMember(taskFile.getGroupId());
            String fullName = userRepo.findById(taskFile.getUserId()).get().getFullName();
            String groupName = groupRepo.findById(taskFile.getGroupId()).get().getName();
            NotificationsDTO noti = new NotificationsDTO(
                    String.format(ContentNotifications.NOTI_CONTENT_UPLOAD_FILE_GROUP_UPDATE, fullName, groupName),
                    TypeNotifications.NOTI_TYPE_UPLOAD_FILE_GROUP, member.getUserId());
            notificationsService.updateNoti(noti);

            return responseEntity.getBody();
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
    public void deleteTaskFile(Long taskFileId) {
        try {
            String url = TaskAPI.API_DELETE_FILE_UPLOAD;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("taskFileId", taskFileId)
                    .build();
            ResponseEntity entity = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, null,
                    String.class);
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public TaskFileDTO createTaskFile(TaskFile taskFile) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TaskExDTO createMark(TaskExDTO taskEx) {
        try {
            String url = TaskAPI.API_CREATE_MARK_TASK;
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            HttpEntity httpEntity = new HttpEntity<>(taskEx, headers);
            ResponseEntity<TaskExDTO> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
                    TaskExDTO.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TaskDetailDTO> getAllTaskFile(Long exId, Long groupId) {
        try {
            String url = TaskAPI.API_GET_ALL_TASK_FILE;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("exId", exId)
                    .queryParam("groupId", groupId)
                    .build();

            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Object.class);
            return (List<TaskDetailDTO>) entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TaskDetailDTO> getAllTaskExByEx(Long exId) {
        try {
            String url = TaskAPI.API_GET_ALL_TASK_EX;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("exId", exId)
                    .build();

            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Object.class);
            return (List<TaskDetailDTO>) entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
    }
    }

    @Override
    public List<TaskExDetailDTO> getAllTaskExByUserId(Long userId) {
        try {
            String url = TaskAPI.API_GET_ALL_TASK_EX_NOTi;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("userId", userId)
                    .build();

            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Object.class);
            return (List<TaskExDetailDTO>) entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
