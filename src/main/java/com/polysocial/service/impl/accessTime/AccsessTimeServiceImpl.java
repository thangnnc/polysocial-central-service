package com.polysocial.service.impl.accessTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.AccessTimeDTO;
import com.polysocial.entity.AccessTime;
import com.polysocial.repo.AccessTimeRepo;
import com.polysocial.service.accessTime.AccessTimeService;

@Service
public class AccsessTimeServiceImpl implements AccessTimeService{

    @Autowired
    private AccessTimeRepo accessTimeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AccessTimeDTO createAccsessTime(Long userId) {
        AccessTime accessTime = new AccessTime();
        accessTime.setUserId(userId);
        return  modelMapper.map(accessTimeRepo.save(accessTime), AccessTimeDTO.class);
    }

    @Override
    public List<AccessTimeDTO> getAllAccessTime() {
        return accessTimeRepo.findAll().stream().map(accessTime -> modelMapper.map(accessTime, AccessTimeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Integer getAccessTimeStatistical(Integer years, Integer monthFirst,Integer monthLast) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        if(monthFirst == 0 && monthLast == 0){
            formatter = new SimpleDateFormat("MM");
            monthLast = 5; 
            monthFirst = monthLast- 6;
            for(int i = 6; i >= 1; i--){
                if(monthLast == i){
                    monthFirst = i + 6;
                    break;
                }
            }
        }
        if(years == 0 ){
            SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
            years = Integer.parseInt(formatterYear.format(date));
        }
        int count = 0;
        try{
             count = accessTimeRepo.findByYearAndBetweenMonth(years, monthFirst, monthLast).size();
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }
    
}
