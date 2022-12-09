package com.polysocial.service.users;

import java.util.List;

import com.polysocial.dto.UserDetailDTO;

public interface UserDetailService {
    
    UserDetailDTO updateUserDetail(UserDetailDTO userDetailDTO);

    List<UserDetailDTO> getAllUserDetail();
}