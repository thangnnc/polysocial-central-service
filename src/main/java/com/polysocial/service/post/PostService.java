package com.polysocial.service.post;

import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;

public interface PostService {

	ListPostDTO getAllPosts(Integer page, Integer limit);

	PostDTO createPost(PostDTO dto, Long tokenId);

}
