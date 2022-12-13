package com.polysocial.service.task;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.polysocial.dto.TaskDetailDTO;
import com.polysocial.dto.TaskExDTO;
import com.polysocial.dto.TaskExDetailDTO;
import com.polysocial.dto.TaskFileCreateDTO;
import com.polysocial.dto.TaskFileDTO;
import com.polysocial.entity.TaskEx;
import com.polysocial.entity.TaskFile;

public interface TaskService {
    
    TaskFileDTO createTaskFile(TaskFile taskFile);

    void deleteTaskFile(Long taskFileId);

    Object saveFile(MultipartFile file, TaskFileCreateDTO taskFile);

    Object updateFile(MultipartFile file, TaskFileCreateDTO taskFile);

    TaskFile getFileUploadGroup(Long exId, Long userId, Long groupId);

    TaskExDTO createTaskEx(TaskEx taskExDTO);

    TaskExDTO updateTaskEx(Long taskId,TaskEx taskExDTO);

    void deleteTaskEx(Long taskId);

    TaskExDTO createMark(TaskExDTO taskEx);

    List<TaskDetailDTO> getAllTaskFile(Long exId, Long groupId);

    List<TaskDetailDTO> getAllTaskExByEx(Long exId);

    List<TaskExDetailDTO> getAllTaskExByUserId(Long userId);

}
