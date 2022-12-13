package com.polysocial.rest.controller.exercise;

import com.polysocial.config.jwt.JwtTokenProvider;
import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.ExercisesDTO;
import com.polysocial.dto.TaskExDTO;
import com.polysocial.service.exerciseQuiz.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private JwtTokenProvider jwt;

    @PostMapping(value = CentralAPI.API_CREATE_EXERCISES, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createExercise(@RequestBody ExercisesDTO e, @RequestHeader("Authorization") String token) {
        try {
            return ResponseEntity.ok().body(exerciseService.createOne(e, jwt.getIdFromJWT(token)));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping(value = CentralAPI.API_UPDATE_EXERCISES, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateExercise(@RequestBody ExercisesDTO e) {
        try {
            return ResponseEntity.ok().body(exerciseService.updateOne(e));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping(value = CentralAPI.API_DELETE_EXERCISES, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteExercise(@RequestParam Long exId) {
        try {
            exerciseService.deleteOne(exId);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_ALL_EXERCISES_END_DATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllExercise(@RequestParam Long groupId) {
        try {
            return ResponseEntity.ok().body(exerciseService.getAllExercisesEndDate(groupId));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_ALL_EXERCISES, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllExercises(@RequestParam Long groupId) {
        try {
            return ResponseEntity.ok().body(exerciseService.getAllExercises(groupId));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = CentralAPI.API_GET_ONE_EXERCIES, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getExercisesById(@RequestParam Long exId, @RequestHeader("Authorization") String token) {
        try {
            return ResponseEntity.ok().body(exerciseService.getExercisesById(exId, jwt.getIdFromJWT(token)));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
