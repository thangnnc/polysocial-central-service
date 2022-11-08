package com.polysocial.service.task;

import org.springframework.web.multipart.MultipartFile;

import com.polysocial.dto.TaskExDTO;
import com.polysocial.dto.TaskFileDTO;
import com.polysocial.entity.TaskEx;
import com.polysocial.entity.TaskFile;

public interface TaskService {
    
    TaskFileDTO createTaskFile(TaskFile taskFile);

    void deleteTaskFile(Long taskFileId);

    TaskFile saveFile(MultipartFile file, Long exId, Long userId, Long groupId);

    TaskFile updateFile(MultipartFile file, Long exId, Long userId, Long groupId);

    TaskFile getFileUploadGroup(Long exId, Long userId, Long groupId);

    TaskExDTO createTaskEx(TaskEx taskExDTO);

    TaskExDTO updateTaskEx(Long taskId,TaskEx taskExDTO);

    void deleteTaskEx(Long taskId);
}
