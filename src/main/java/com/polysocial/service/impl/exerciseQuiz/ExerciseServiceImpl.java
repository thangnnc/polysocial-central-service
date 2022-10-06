package com.polysocial.service.impl.exerciseQuiz;

import com.polysocial.consts.DemoAPI;
import com.polysocial.consts.ExerciseAPI;
import com.polysocial.dto.DemoDTO;
import com.polysocial.dto.ExerciseDTO;
import com.polysocial.service.DemoService;
import com.polysocial.service.exerciseQuiz.ExerciseService;
import com.polysocial.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ExerciseDTO getExercise() {
        Logger.info("Start getExercise service");
        try {
            String url = ExerciseAPI.API_GET_EXERCISE;

            ResponseEntity<ExerciseDTO> entity = restTemplate.exchange(url, HttpMethod.GET, null, ExerciseDTO.class);
            ExerciseDTO exDto = entity.getBody();
            return exDto;
        }catch (Exception ex){
            Logger.error("Get exercise exception: " + ex);
            return null;
        }
    }
}
