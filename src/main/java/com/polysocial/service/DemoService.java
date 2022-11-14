package com.polysocial.service;

import com.polysocial.dto.DemoDTO;
import com.polysocial.entity.Friends;

import java.util.List;

public interface DemoService {

    List<Friends> getDemo();
}
