package com.polysocial.service.impl.post;

import com.polysocial.consts.PostAPI;
import com.polysocial.dto.PostDTO;
import com.polysocial.service.post.PostService;
import com.polysocial.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PostDTO getPost() {
        Logger.info("Start getPost service");
        try {
            String url = PostAPI.API_GET_POST;

            ResponseEntity<PostDTO> entity = restTemplate.exchange(url, HttpMethod.GET, null, PostDTO.class);
            PostDTO exDto = entity.getBody();
            return exDto;
        }catch (Exception ex){
            Logger.error("Get post exception: " + ex);
            return null;
        }
    }
}
