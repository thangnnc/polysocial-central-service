package com.polysocial.rest.controller.statistical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.StatisticalAPI;
import com.polysocial.service.statistical.StatisticalService;

@RestController
public class StatisticalController {
    
    @Autowired
    private StatisticalService statisticalService;

    @GetMapping(StatisticalAPI.API_GET_STATISTICAL)
    public ResponseEntity getStatistical() {
        try {
            return ResponseEntity.ok(statisticalService.getStatistical());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
