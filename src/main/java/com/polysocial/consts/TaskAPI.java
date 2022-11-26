package com.polysocial.consts;

import org.springframework.stereotype.Component;

@Component
public class TaskAPI {
    
    public static final String API_TASK_FILE_CREATE = HostURL.GROUP_HOST + "/api/task/file/create";

    public static final String API_TASK_FILE_UPDATE = HostURL.GROUP_HOST + "/api/task/file/update";

    public static final String API_GET_FILE_UPLOAD_GROUP =HostURL.GROUP_HOST +  "/api/task/file/get-file";

    public static final String API_DELETE_FILE_UPLOAD = HostURL.GROUP_HOST + "/api/task/file/delete-file";


}
