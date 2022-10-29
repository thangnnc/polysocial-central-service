package com.polysocial.service.impl.group;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.polysocial.consts.GroupAPI;
import com.polysocial.consts.PostAPI;
import com.polysocial.dto.GroupDTO;
import com.polysocial.dto.ListPostDTO;
import com.polysocial.entity.Groups;
import com.polysocial.entity.Members;
import com.polysocial.entity.Users;
import com.polysocial.service.group.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<GroupDTO> getAll(Integer page, Integer limit) {
        try {
            String url = GroupAPI.API_GET_ALL_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("page",page)
                    .queryParam("limit", limit)
                         .build();
                
            ResponseEntity<GroupDTO[]> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, GroupDTO[].class);
            return Arrays.asList(entity.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Groups getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addMemberToGroup(Users user, Groups group) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteMemberToGroup(Long groupId, Long userId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteGroup(Long groupId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Object getTeacherFromGroup(Long groupId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getUserId(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Groups createGroup(Groups group) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users getOneMemberInGroup(String email, Long groupId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Members> getMemberInGroup(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Groups> findByGroupName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Users getUserById(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateGroup(String name, Integer totalMember, String description, Long groupId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void createExcel(MultipartFile multipartFile) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Groups> findByKeywork(String keywork) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Members saveMember(Long userId, Long groupId) {
        // TODO Auto-generated method stub
        return null;
    }



}
