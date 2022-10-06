package com.polysocial.rest.controller.demo;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.DemoDTO;
import com.polysocial.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping(CentralAPI.GET_DEMO)
    public DemoDTO getDemo(){
        DemoDTO response = demoService.getDemo();
        return response;
    }
}
