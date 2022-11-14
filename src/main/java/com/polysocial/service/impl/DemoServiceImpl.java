package com.polysocial.service.impl;

import com.polysocial.consts.DemoAPI;
import com.polysocial.dto.DemoDTO;
import com.polysocial.service.DemoService;
import com.polysocial.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public DemoDTO getDemo() {
        Logger.info("Start getDemo service");
        try {
            String url = DemoAPI.API_GET_DEMO;

            ResponseEntity<DemoDTO> entity = restTemplate.exchange(url, HttpMethod.GET, null, DemoDTO.class);
            DemoDTO demoDTO = entity.getBody();
            return demoDTO;
        }catch (Exception ex){
            Logger.error("Get demo exception: " + ex);
            return null;
        }
    }
}
