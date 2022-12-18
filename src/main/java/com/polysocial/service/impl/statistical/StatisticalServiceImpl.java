package com.polysocial.service.impl.statistical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.StatisticalDTO;
import com.polysocial.repo.StatisticalRepo;
import com.polysocial.service.statistical.StatisticalService;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    @Autowired
    private StatisticalRepo statisticalRepo;

    @Override
    public StatisticalDTO getStatistical() {
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        statisticalDTO.setTotalStudent(statisticalRepo.countStudent());
        statisticalDTO.setTotalTeacher(statisticalRepo.countTeacher());
        statisticalDTO.setTotalGroup(statisticalRepo.countGroups());
        return statisticalDTO;
    }
    
}
