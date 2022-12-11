package com.polysocial.service.exerciseQuiz;

import java.util.List;
import com.polysocial.dto.ExercisesDTO;

public interface ExerciseService {

    ExercisesDTO createOne(ExercisesDTO exercise, Long userId);

    ExercisesDTO updateOne(ExercisesDTO exercise);

    ExercisesDTO deleteOne(ExercisesDTO exercise);

    List<Object> getAllExercisesEndDate(Long groupId);

    List<ExercisesDTO> getAllExercises(Long groupId);

    ExercisesDTO getExercisesById(Long exerciseId);
}
