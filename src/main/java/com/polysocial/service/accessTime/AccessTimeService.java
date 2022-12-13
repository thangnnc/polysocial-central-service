package com.polysocial.service.accessTime;

import java.util.List;
import java.util.Optional;

import com.polysocial.dto.AccessTimeDTO;

public interface AccessTimeService {
    
    AccessTimeDTO createAccsessTime(Long userId);
    
    List<AccessTimeDTO> getAllAccessTime();

    Integer getAccessTimeStatistical(Integer years, Integer monthFirst, Integer monthLast);
}
