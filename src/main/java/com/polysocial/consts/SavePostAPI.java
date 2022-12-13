package com.polysocial.consts;

import org.springframework.stereotype.Component;

@Component
public class SavePostAPI {
    public static final String API_SAVE_POST = HostURL.POST_HOST+"/save-post";
    public static final String API_GET_ALL_SAVE_POST = HostURL.POST_HOST+"/get-all-save-post";
    public static final String API_DELETE_SAVE_POST = HostURL.POST_HOST+"/delete-save-post";
    
}
