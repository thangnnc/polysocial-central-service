package com.polysocial.consts;

import org.springframework.stereotype.Component;

@Component
public class PostAPI {

    public static final String API_GET_ALL_POSTS = HostURL.POST_HOST + "/api/post";
    
    public static final String API_CREATE_POST = HostURL.POST_HOST + "/api/post";

    public static final String API_UPDATE_POST = HostURL.POST_HOST + "/api/post/update";
    
    public static final String API_UPLOADFILE_POST = HostURL.POST_HOST + "/api/upload";

    public static final String API_DELETE_POST =  HostURL.POST_HOST + "/api/post/delete";

    public static final String API_GET_ONE_POST =  HostURL.POST_HOST + "/api/get-post";

    public static final String API_GET_POST_BY_GROUP =  HostURL.POST_HOST +"/api/get-post-by-group";


}
