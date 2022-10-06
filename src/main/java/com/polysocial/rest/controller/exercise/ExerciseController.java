package com.polysocial.rest.controller.exercise;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.DemoDTO;
import com.polysocial.dto.ExerciseDTO;
import com.polysocial.service.DemoService;
import com.polysocial.service.exerciseQuiz.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping(CentralAPI.GET_EXERCISE)
    public ExerciseDTO getExercise(){
        ExerciseDTO response = exerciseService.getExercise();
        return response;
    }
}
