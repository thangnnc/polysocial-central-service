package com.polysocial.consts;

import org.springframework.stereotype.Component;

@Component
public class PostAPI {

    public static final String API_GET_ALL_POSTS = HostURL.POST_HOST + "/api/post";
    
    public static final String API_CREATE_POST = HostURL.POST_HOST + "/api/post";
    
    public static final String API_UPLOADFILE_POST = HostURL.POST_HOST + "/api/upload";
}
