package com.polysocial.service.impl;

import com.polysocial.consts.DemoAPI;
import com.polysocial.dto.DemoDTO;
import com.polysocial.entity.Friends;
import com.polysocial.repo.FriendRepo;
import com.polysocial.service.DemoService;
import com.polysocial.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FriendRepo friendRepo;

    @Override
    public List<Friends> getDemo() {
        Logger.info("Start getDemo service");
        List<Friends> listFriends = null;
        try {
            listFriends = friendRepo.findAll();
        }catch (Exception ex){
            Logger.error("Get demo exception: " + ex);
        }
        return listFriends;
    }
}
