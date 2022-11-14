package com.polysocial.consts;

public class CentralAPI {

    public static final String GET_DEMO = "/api/demo-detail";
    public static final String GET_MESSAGE = "/api/message-detail";
    // public static final String GET_POST = "/api/post-detail";
    public static final String API_CREATE_TASK_FILE = "/task-file/create";
    public static final String API_UPDATE_TASK_FILE = "/task-file/update";
    public static final String API_GET_TASK_FILE_UPLOAD = "/task-file/get-file";
    public static final String API_DELETE_TASK_FILE = "/task-file/delete-file";


    public static final String API_CREATE_EXERCISES = "/exercises/create";
    public static final String API_UPDATE_EXERCISES = "/exercises/update";
    public static final String API_DELETE_EXERCISES = "/exercises/delete";
    public static final String API_GET_ALL_EXERCISES_END_DATE = "/exercises/get-all-exercises-end-date";

    public static final String GET_ALL_POST = "/api/posts";

    // group
    public static final String GET_ALL_GROUP = "/group/api/get/all";
    public static final String GET_ALL_GROUP_FALSE = "/group/api/get/all/false";
    public static final String GET_ONE_GROUP = "/group/api/get/class";
    public static final String DELETE_MEMBER_GROUP = "/group/api/remove-student";
    public static final String API_DELETE_GROUP = "/group/api/delete-group";

    public static final String API_GET_TEACHER = "/group/api/get-teacher";
    public static final String API_CREATE_GROUP = "/group/api/create-group";
    public static final String API_CREATE_MEMBER = "/group/api/create-student";
    public static final String API_GET_ONE_STUDENT = "/group/api/get-student";

    public static final String API_GET_MEMBER_GROUP = "/group/api/get/all-student";
    public static final String API_CREATE_GROUP_EXCEL = "/group/api/create-file";
    public static final String API_FIND_GROUP_BY_KEYWORK = "/group/api/find-group";
    public static final String API_UPDATE_GROUP = "/group/api/update-group";

    public static final String API_GET_ALL_GROUP_BY_STUDENT = "/group/api/get-all/student";
    public static final String API_GET_ALL_GROUP_BY_TEACHER = "/group/api/get-all/teacher";

}
