package com.polysocial.service.post;

import java.util.List;

import com.polysocial.dto.ListPostDTO;
import com.polysocial.dto.PostDTO;
import com.polysocial.dto.SavePostDTO;
import com.polysocial.dto.SavePostDetailDTO;

public interface PostService {

	Object getAllPosts(Integer page, Integer limit);

	ListPostDTO findAllPageByGroup(Long groupId, Integer page, Integer limit);

	PostDTO createPost(PostDTO dto, Long tokenId);

	PostDTO updatePost(PostDTO dto, Long tokenId);

	PostDTO getOne(Long postId);

	void delete(Long postId);

	SavePostDTO savePost(SavePostDTO savePostDTO);

    SavePostDTO[] getAllSavePost(Long userId);

    void deleteSavePost(Long savePostId);


}
