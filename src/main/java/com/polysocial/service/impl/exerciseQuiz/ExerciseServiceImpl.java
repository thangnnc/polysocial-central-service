package com.polysocial.service.impl.exerciseQuiz;

import com.polysocial.consts.ExerciseAPI;
import com.polysocial.dto.ExercisesDTO;
import com.polysocial.dto.NotificationsDTO;
import com.polysocial.entity.Members;
import com.polysocial.notification.ContentNotifications;
import com.polysocial.repo.MemberRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.exerciseQuiz.ExerciseService;
import com.polysocial.service.notifications.NotificationsService;
import com.polysocial.type.TypeNotifications;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired UserRepo userRepo;

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
    public ExercisesDTO deleteOne(ExercisesDTO exercise) {
        try {
            String url = ExerciseAPI.API_DELETE_EXERCISES;
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity entity = new HttpEntity(exercise, headers);
            ResponseEntity<ExercisesDTO> response = restTemplate.exchange(url, HttpMethod.DELETE,
                    entity, ExercisesDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

}
