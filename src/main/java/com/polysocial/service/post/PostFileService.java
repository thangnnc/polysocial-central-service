package com.polysocial.service.post;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public interface PostFileService {

	List<String> saveFile(List<MultipartFile> fi);

}
