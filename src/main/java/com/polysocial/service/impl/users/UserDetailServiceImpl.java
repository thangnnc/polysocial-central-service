package com.polysocial.service.impl.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.UserDetailDTO;
import com.polysocial.entity.UserDetail;
import com.polysocial.repo.UserDetailRepo;
import com.polysocial.service.users.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService{

    @Autowired
    private UserDetailRepo userDetailRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetailDTO updateUserDetail(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = modelMapper.map(userDetailDTO, UserDetail.class);
        userDetailRepo.save(userDetail);
  
        return userDetailDTO;
    }

    
}
