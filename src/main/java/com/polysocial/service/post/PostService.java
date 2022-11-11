package com.polysocial.service.post;

import com.polysocial.dto.ListPostDTO;

public interface PostService {

	ListPostDTO getAllPosts(Integer page, Integer limit);
}
