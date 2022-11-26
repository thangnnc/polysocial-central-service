package com.polysocial.consts;

import org.springframework.stereotype.Component;

@Component
public class CommentAPI {
	
	public static final String API_CREATE_COMMENT = HostURL.POST_HOST + "/api/comment";
	
	public static final String API_GET_COMMENT_BY_POST = HostURL.POST_HOST + "/api/comment/post";

    public static final String API_PUT_COMMENT = HostURL.POST_HOST +"/api/comment";

    public static final String API_DELETE_COMMENT = HostURL.POST_HOST +"/api/comment/delete";

    public static final String API_REPLY_COMMENT = HostURL.POST_HOST +"/api/comment/reply";
}
