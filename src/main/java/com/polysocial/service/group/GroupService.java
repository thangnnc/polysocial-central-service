package com.polysocial.service.group;

import java.io.IOException;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.polysocial.dto.GroupDTO;
import com.polysocial.dto.MemberDTO;
import com.polysocial.dto.MemberGroupDTO;
import com.polysocial.dto.PageObject;
import com.polysocial.dto.StudentDTO;
import com.polysocial.dto.UserDTO;

public interface GroupService {

    PageObject<GroupDTO> getAll(Integer page, Integer limit);
    
    GroupDTO getOne(Long id);
    
    GroupDTO updateGroup(GroupDTO group);
        
    String deleteMemberToGroup(Long groupId, Long userId);
    
    GroupDTO deleteGroup(Long groupId);
    
    UserDTO getTeacherFromGroup(Long groupId);
    
    Integer getUserId(String email);
    
    GroupDTO createGroup(GroupDTO group);
    
    UserDTO getOneMemberInGroup(String email, Long groupId);
    
    List<Object> getMemberInGroup(Long id);
                
    List<MemberDTO> createExcel(MultipartFile multipartFile, Long groupId) throws IOException;
    
    List<GroupDTO> findByKeywork(String keywork, Long userId);
    
    MemberDTO saveMember(StudentDTO student);
    
    List<MemberGroupDTO> getAllGroupByStudent(Long userId);
    
    List<MemberGroupDTO> getAllGroupByTeacher(Long userId);

    PageObject<GroupDTO> getAllGroupFalse(Integer page, Integer limit);

    List<MemberGroupDTO> getAllGroupByUser(Long userId);

    
    

}
