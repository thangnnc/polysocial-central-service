package com.polysocial.service.impl.group;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.polysocial.consts.GroupAPI;
import com.polysocial.dto.GroupDTO;
import com.polysocial.dto.PageObject;
import com.polysocial.dto.StudentDTO;
import com.polysocial.dto.MemberDTO;
import com.polysocial.entity.Groups;
import com.polysocial.entity.Users;
import com.polysocial.service.group.GroupService;
import com.twilio.rest.supersim.v1.UsageRecord.Group;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PageObject<GroupDTO> getAll(Integer page, Integer limit) {
        try {
            String url = GroupAPI.API_GET_ALL_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("page", page)
                    .queryParam("limit", limit)
                    .build();
            ResponseEntity<PageObject> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    PageObject.class);
            PageObject<GroupDTO> list = entity.getBody();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GroupDTO getOne(Long id) {
        try {
            String url = GroupAPI.API_GET_ONE_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId", id)
                    .build();

            ResponseEntity<GroupDTO> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    GroupDTO.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String deleteMemberToGroup(Long groupId, Long userId) {
        try {
            String url = GroupAPI.API_REMOVE_STUDENT;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId",groupId)
                    .queryParam("userId", userId)
                    .build();
            ResponseEntity<String> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, null,
                    String.class);
            return "OK";
        } catch (Exception e) {
           e.printStackTrace();
            return null;
        }
    }

    @Override
    public GroupDTO deleteGroup(Long groupId) {
        try {
            String url = GroupAPI.API_DELETE_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId",groupId)
                    .build();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity request = new HttpEntity(header);
            ResponseEntity<Groups> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, request,
            Groups.class);
            GroupDTO groupDTO = modelMapper.map(responseEntity.getBody(), GroupDTO.class);
            return groupDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Object getTeacherFromGroup(Long groupId) {
        try {
            String url = GroupAPI.API_GET_TEACHER;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId", groupId)
                    .build();
            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Object.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer getUserId(String email) {
        return null;
    }

    @Override
    public GroupDTO createGroup(GroupDTO group) {
        try {
            String url = GroupAPI.API_CREATE_GROUP;
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            System.out.println(group.getName());
            HttpEntity entity = new HttpEntity(group, header);
            ResponseEntity<GroupDTO> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
            GroupDTO.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Users getOneMemberInGroup(String email, Long groupId) {
        try {
            String url = GroupAPI.API_GET_ONE_STUDENT;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("email", email)
                    .queryParam("groupId", groupId)
                    .build();
            ResponseEntity<Users> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Users.class);
            return entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getMemberInGroup(Long id) {
        try {
            String url = GroupAPI.API_GET_MEMBER_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId", id)
                    .build();
            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Object.class);
            return (List<Object>) entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object createExcel(MultipartFile file) throws IOException {
        try {
            String url = GroupAPI.API_CREATE_GROUP_EXCEL;
            Path tempFile = Files.createTempFile(null, null);

            Files.write(tempFile, file.getBytes());
            File fileToSend = tempFile.toFile();

            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();

            parameters.add("file", new FileSystemResource(fileToSend));

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "multipart/form-data");
            HttpEntity httpEntity = new HttpEntity<>(parameters, headers);

            ResponseEntity<String> group = restTemplate.exchange(url, HttpMethod.POST,
                    httpEntity, String.class);
            return group.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<GroupDTO> findByKeywork(String keywork) {
        try {
            String url = GroupAPI.API_FIND_GROUP_BY_KEYWORK;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("keywork", keywork)
                    .build();
            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Object.class);
            List<GroupDTO> groupDTO = ((Collection<Object>) entity.getBody()).stream()
                    .map(item -> modelMapper.map(item, GroupDTO.class))
                    .collect(Collectors.toList());
            return groupDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MemberDTO saveMember(StudentDTO student) {
        try {
            String url = GroupAPI.API_CREATE_MEMBER;
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity(student, header);
            ResponseEntity<StudentDTO> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
            StudentDTO.class);
            MemberDTO memberDTO = modelMapper.map(responseEntity.getBody(), MemberDTO.class);
            return memberDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GroupDTO updateGroup(GroupDTO group) {
        try {
            String url = GroupAPI.API_UPDATE_GROUP;
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity(group, header);
            ResponseEntity<GroupDTO> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity,
            GroupDTO.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MemberDTO> getAllGroupByStudent(Long userId) {
        try {
            String url = GroupAPI.API_GET_ALL_GROUP_BY_STUDENT;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("userId", userId)
                    .build();
            ResponseEntity<MemberDTO> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    MemberDTO.class);
            return (List<MemberDTO>) entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getAllGroupByTeacher(Long userId) {
        try {
            String url = GroupAPI.API_GET_ALL_GROUP_BY_TEACHER;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("userId", userId)
                    .build();
            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Object.class);
            return (List<Object>) entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PageObject<GroupDTO> getAllGroupFalse(Integer page, Integer limit) {
        try {
            String url = GroupAPI.API_GET_ALL_GROUP_FALSE;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("page", page)
                    .queryParam("limit", limit)
                    .build();
            ResponseEntity<PageObject> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    PageObject.class);
            PageObject<GroupDTO> list = entity.getBody();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
