package com.polysocial.service.like;

import com.polysocial.dto.LikeDTO;

public interface LikeService {

	LikeDTO likeUnLike(LikeDTO dto, Long tokenId);

}
