package com.polysocial.rest.controller.message;

import com.polysocial.consts.CentralAPI;
import com.polysocial.dto.MessageDTO;
import com.polysocial.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(CentralAPI.GET_MESSAGE)
    public MessageDTO getMessage(){
        MessageDTO response = messageService.getMessage();
        return response;
    }
}
