package com.polysocial.service.impl.group;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.AbstractDocument.Content;

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
import com.polysocial.dto.UserDTO;
import com.polysocial.dto.MemberDTO;
import com.polysocial.dto.MemberGroupDTO;
import com.polysocial.dto.NotificationsDTO;
import com.polysocial.entity.Contacts;
import com.polysocial.entity.Groups;
import com.polysocial.entity.Members;
import com.polysocial.entity.Messages;
import com.polysocial.entity.RoomChats;
import com.polysocial.entity.ViewedStatus;
import com.polysocial.notification.ContentNotifications;
import com.polysocial.repo.ContactRepo;
import com.polysocial.repo.GroupRepo;
import com.polysocial.repo.MemberRepo;
import com.polysocial.repo.MessageRepo;
import com.polysocial.repo.RoomChatRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.repo.ViewedStatusRepo;
import com.polysocial.service.group.GroupService;
import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.type.TypeNotifications;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificationsService notificationsService;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ViewedStatusRepo viewedStatusRepo;

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private RoomChatRepo roomChatRepo;

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
                    .queryParam("groupId", groupId)
                    .queryParam("userId", userId)
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<String> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, request,
                    String.class);

            String nameGroup = groupRepo.findById(groupId).get().getName();
            String nameAdmin = userRepo.findById(memberRepo.getTeacherByMember(groupId).getUserId()).get()
                    .getFullName();
            NotificationsDTO notificationsDTO = new NotificationsDTO(
                    String.format(ContentNotifications.NOTI_CONTENT_DELETE_USER_GROUP, nameAdmin, nameGroup),
                    TypeNotifications.NOTI_TYPE_DELETE_MEMBER_GROUP, userId);
            notificationsService.createNoti(notificationsDTO);
            return "OK";
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public GroupDTO deleteGroup(Long groupId) {
        try {
            String url = GroupAPI.API_DELETE_GROUP;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId", groupId)
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<Groups> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, request,
                    Groups.class);
            GroupDTO groupDTO = modelMapper.map(entity.getBody(), GroupDTO.class);
            return groupDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public UserDTO getTeacherFromGroup(Long groupId) {
        try {
            String url = GroupAPI.API_GET_TEACHER;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId", groupId)
                    .build();
            ResponseEntity<UserDTO> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    UserDTO.class);
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
    public UserDTO getOneMemberInGroup(String email, Long groupId) {
        try {
            String url = GroupAPI.API_GET_ONE_STUDENT;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("email", email)
                    .queryParam("groupId", groupId)
                    .build();
            ResponseEntity<UserDTO> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    UserDTO.class);
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
    public List<MemberDTO> createExcel(MultipartFile file, Long groupId) throws IOException {
        try {
            String url = GroupAPI.API_CREATE_GROUP_EXCEL;
            Path tempFile = Files.createTempFile(null, null);
            Files.write(tempFile, file.getBytes());
            File fileToSend = tempFile.toFile();
            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
            Long teacherId = memberRepo.getTeacherByMember(groupId).getUserId();
            parameters.add("file", new FileSystemResource(fileToSend));
            parameters.add("groupId", groupId);
            parameters.add("teacherId", teacherId);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "multipart/form-data");
            HttpEntity httpEntity = new HttpEntity<>(parameters, headers);
            ResponseEntity<Object> group = restTemplate.exchange(url, HttpMethod.POST,
                    httpEntity, Object.class);
            List<Members> member = memberRepo.findByGroupId(groupId);

            Long roomChatId = roomChatRepo.getRoomChatByGroupId(groupId).getRoomId();
            RoomChats room = roomChatRepo.findById(roomChatId).get();
            room.setLastMessage("Có thành viên vừa tham gia nhóm");
            roomChatRepo.save(room);
            for (Members members : member) {
                String nameTeacher = userRepo.findById(memberRepo.getTeacherByMember(groupId).getUserId()).get()
                        .getFullName();
                String nameGroup = groupRepo.findById(groupId).get().getName();
                NotificationsDTO notiDTO = new NotificationsDTO(
                        String.format(ContentNotifications.NOTI_CONTENT_ADD_MEMBER_GROUP, nameTeacher, nameGroup),
                        TypeNotifications.NOTI_TYPE_ADD_MEMBER_GROUP, members.getUserId());
                notificationsService.createNoti(notiDTO);

                List<Contacts> getContact = contactRepo.getContactByUserIdAndRoomIdContacts(members.getUserId(),
                        roomChatId);
                Contacts contact = new Contacts(members.getUserId(), roomChatId);
                for (Contacts contacts : getContact) {
                    Long contactId = contacts.getContactId();
                    if(contactId == null) continue;
                    if (getContact == null) {
                        contact = new Contacts();
                        contactRepo.save(contact);
                        contactId = contact.getContactId();
                        Messages message = new Messages(
                                userRepo.findById(members.getUserId()).get().getFullName() + " đã tham gia nhóm",
                                false);
                        String encodedString = Base64.getEncoder().encodeToString(message.getContent().getBytes());
                        message.setContent(encodedString);
                        message.setContact(contact);
                        messageRepo.save(message);
                    }
                    Messages message = new Messages(
                            userRepo.findById(members.getUserId()).get().getFullName() + " đã tham gia nhóm", false);
                    String encodedString = Base64.getEncoder().encodeToString(message.getContent().getBytes());
                    message.setContent(encodedString);
                    message.setContact(getContact.get(0));
                    messageRepo.save(message);

                    ViewedStatus viewedStatus = new ViewedStatus();
                    viewedStatus.setContactId(contactId);
                    viewedStatusRepo.save(viewedStatus);
                }

            }
            return (List<MemberDTO>) group.getBody();
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

            List<Members> listMember = memberRepo.findByGroupId(group.getGroupId());
            String nameAdmin = userRepo.findById(memberRepo.getTeacherByMember(group.getGroupId()).getUserId()).get()
                    .getFullName();
            String nameGroup = groupRepo.findById(group.getGroupId()).get().getName();
            for (Members member : listMember) {
                NotificationsDTO noti = new NotificationsDTO(
                        String.format(ContentNotifications.NOTI_CONTENT_UPDATE_GROUP, nameAdmin, nameGroup),
                        TypeNotifications.NOTI_TYPE_UPDATE_GROUP, member.getUserId());
                notificationsService.createNoti(noti);
            }
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MemberGroupDTO> getAllGroupByStudent(Long userId) {
        try {
            String url = GroupAPI.API_GET_ALL_GROUP_BY_STUDENT;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("userId", userId)
                    .build();
            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Object.class);
            return (List<MemberGroupDTO>) entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MemberGroupDTO> getAllGroupByTeacher(Long userId) {
        try {
            String url = GroupAPI.API_GET_ALL_GROUP_BY_TEACHER;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("userId", userId)
                    .build();
            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Object.class);
            return (List<MemberGroupDTO>) entity.getBody();
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

    @Override
    public List<MemberGroupDTO> getAllGroupByUser(Long userId) {
        try {
            String url = GroupAPI.API_GET_ALL_GROUP_BY_USER;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("userId", userId)
                    .build();
            ResponseEntity<Object> entity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                    Object.class);
            return (List<MemberGroupDTO>) entity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
