package com.polysocial.service.impl.exerciseQuiz;

import com.polysocial.consts.ExerciseAPI;
import com.polysocial.dto.ExercisesDTO;
import com.polysocial.dto.ExercisesDetailDTO;
import com.polysocial.dto.NotificationsDTO;
import com.polysocial.entity.Members;
import com.polysocial.entity.Users;
import com.polysocial.notification.ContentNotifications;
import com.polysocial.repo.GroupRepo;
import com.polysocial.repo.MemberRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.exerciseQuiz.ExerciseService;
import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.type.TypeNotifications;
import com.polysocial.utils.SendMailDeadline;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationsService notificationsService;

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SendMailDeadline sendMailDeadline;

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public ExercisesDTO createOne(ExercisesDTO exercise, Long userId) {
        try {
            String url = ExerciseAPI.API_CREATE_EXERCISES;
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity(exercise, headers);
            ResponseEntity<ExercisesDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                    ExercisesDTO.class);
            List<Members> members = memberRepo.findByGroupId(exercise.getGroupId());
            String fullName = userRepo.findById(userId).get().getFullName();
            for (Members member : members) {
                NotificationsDTO notiDTO = new NotificationsDTO(String.format(ContentNotifications.NOTI_CONTENT_CREATE_DEADLINE, fullName), TypeNotifications.NOTI_TYPE_CREATE_DEADLINE, member.getUserId());
                notiDTO.setUser(member.getUserId());
                notificationsService.createNoti(notiDTO);
                Users user = userRepo.findById(member.getUserId()).get();
                String nameTeacher = userRepo.findById(memberRepo.getTeacherByMember(exercise.getGroupId()).getUserId()).get().getFullName();
                sendMailDeadline.sendMail(user.getEmail(), user.getFullName(), groupRepo.findById(exercise.getGroupId()).get().getName(), exercise.getDeadline(), nameTeacher);
            }
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ExercisesDTO updateOne(ExercisesDTO exercise) {
        try {
            String url = ExerciseAPI.API_UPDATE_EXERCISES;
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity(exercise, headers);
            ResponseEntity<ExercisesDTO> response = restTemplate.exchange(url, HttpMethod.PUT, entity,
                    ExercisesDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteOne(Long exId) {
        try {
            String url = ExerciseAPI.API_DELETE_EXERCISES;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("exId", exId).build();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, null,
                    Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Object> getAllExercisesEndDate(Long groupId) {
        try {
            String url = ExerciseAPI.API_GET_ALL_EXERCISES_END_DATE;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId", groupId)
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity(groupId, headers);
            ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                    Object.class);
            return (List<Object>) response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ExercisesDTO> getAllExercises(Long groupId) {
        try {
            String url = ExerciseAPI.API_GET_ALL_EXERCISES;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("groupId", groupId)
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity(groupId, headers);
            ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                    Object.class);
            return (List<ExercisesDTO>) response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getExercisesById(Long exerciseId, Long userId) {
        try {
            String url = ExerciseAPI.API_GET_EXERCISES_BY_ID;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("exId", exerciseId)
                    .queryParam("userId", userId)
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity(exerciseId, headers);
            ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                    Object.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ExercisesDTO> checkEndDate() {
        try{
            String url = ExerciseAPI.API_CHECK_END_DATE;
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<List<ExercisesDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<List<ExercisesDTO>>() {});
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ExercisesDTO> sendNotiDeadline() {
        try{
            String url = ExerciseAPI.API_SEND_NOTI_DEADLINE;
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<List<ExercisesDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<List<ExercisesDTO>>() {});
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
