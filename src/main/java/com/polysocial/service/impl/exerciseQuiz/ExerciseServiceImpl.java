package com.polysocial.service.impl.exerciseQuiz;


import com.polysocial.consts.ExerciseAPI;
import com.polysocial.dto.ExercisesDTO;
import com.polysocial.service.exerciseQuiz.ExerciseService;
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

    @Override
    public ExercisesDTO createOne(ExercisesDTO exercise) {
        try {
            String url = ExerciseAPI.API_CREATE_EXERCISES;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("exercise", exercise)
                    .build();
            HttpEntity entity = new HttpEntity(exercise, new HttpHeaders());
            ResponseEntity<ExercisesDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, ExercisesDTO.class);
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
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("exercise", exercise)
                    .build();
            HttpEntity entity = new HttpEntity(exercise, new HttpHeaders());
            ResponseEntity<ExercisesDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, entity, ExercisesDTO.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ExercisesDTO deleteOne(Long exId) {
        try {
            String url = ExerciseAPI.API_DELETE_EXERCISES;
            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("exId", exId)
                    .build();
            HttpEntity entity = new HttpEntity(exId, new HttpHeaders());
            ResponseEntity<ExercisesDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity, ExercisesDTO.class);
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
            HttpEntity entity = new HttpEntity(groupId, new HttpHeaders());
            ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, Object.class);
            return  (List<Object>) response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
