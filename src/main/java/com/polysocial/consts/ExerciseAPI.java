package com.polysocial.consts;

import org.springframework.stereotype.Component;

@Component
public class ExerciseAPI {

    public static final String API_CREATE_EXERCISES = HostURL.GROUP_HOST+"/api/exercises/create";

    public static final String API_UPDATE_EXERCISES = HostURL.GROUP_HOST+"/api/exercises/update";

    public static final String API_DELETE_EXERCISES = HostURL.GROUP_HOST+"/api/exercises/delete";

    public static final String API_GET_ALL_EXERCISES_END_DATE = HostURL.GROUP_HOST+"/api/exercises/get-all-exercises-end-date";
}
