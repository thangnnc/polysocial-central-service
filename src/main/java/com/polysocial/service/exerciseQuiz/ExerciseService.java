package com.polysocial.service.exerciseQuiz;

import java.util.List;
import com.polysocial.dto.ExercisesDTO;
import com.polysocial.dto.ExercisesDetailDTO;

public interface ExerciseService {

    ExercisesDTO createOne(ExercisesDTO exercise, Long userId);

    ExercisesDTO updateOne(ExercisesDTO exercise);

    void deleteOne(Long exId);

    List<Object> getAllExercisesEndDate(Long groupId);

    List<ExercisesDTO> getAllExercises(Long groupId);

    Object getExercisesById(Long exerciseId, Long userId);
}
