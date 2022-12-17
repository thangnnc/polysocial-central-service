package com.polysocial.service.impl.users;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.UserDTO;
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
    public UserDetailDTO updateUserDetail(UserDetailDTO userDetailDTO, Long userId) {
        if(userId != userDetailDTO.getUserId()){
            return null;
        }
        Long userDetailId = userDetailRepo.findByUserId(userDetailDTO.getUserId()).getUserDetailId();
        userDetailDTO.setUserDetailId(userDetailId);
        UserDetail userDetail = modelMapper.map(userDetailDTO, UserDetail.class);
        userDetailRepo.save(userDetail);
        return userDetailDTO;
    }

    @Override
    public List<UserDetailDTO> getAllUserDetail() {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<UserDetailDTO> listDTO = userDetailRepo.findAll().stream().map(element -> modelMapper.map(element, UserDetailDTO.class))
                .collect(Collectors.toList());
        return listDTO;
    }

    @Override
    public UserDetailDTO getOne(Long userDetailId) {
        UserDetail user = userDetailRepo.findByUserId(userDetailId);
        return modelMapper.map(user, UserDetailDTO.class);
    }

    

    
}
