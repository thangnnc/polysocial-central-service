package com.polysocial.service.impl.message;

import com.polysocial.consts.MessageAPI;
import com.polysocial.dto.MessageDTO;
import com.polysocial.service.message.MessageService;
import com.polysocial.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public MessageDTO getMessage() {
        Logger.info("Start getMessage service");
        try {
            String url = MessageAPI.API_GET_MESSAGE;

            ResponseEntity<MessageDTO> entity = restTemplate.exchange(url, HttpMethod.GET, null, MessageDTO.class);
            MessageDTO exDto = entity.getBody();
            return exDto;
        }catch (Exception ex){
            Logger.error("Get message exception: " + ex);
            return null;
        }
    }
}
