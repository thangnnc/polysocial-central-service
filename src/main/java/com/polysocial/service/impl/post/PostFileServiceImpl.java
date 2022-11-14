package com.polysocial.service.impl.post;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.polysocial.consts.PostAPI;
import com.polysocial.service.post.PostFileService;

@Service
public class PostFileServiceImpl implements PostFileService {

	@Autowired
	private RestTemplate restTemplate;

//	public PostFileUploadDTO saveFile(MultipartFile fi) {
//		try {
//			String url = PostAPI.API_UPLOADFILE_POST;
//			Path tempFile = Files.createTempFile(null, null);
//
//			Files.write(tempFile, fi.getBytes());
//			File fileToSend = tempFile.toFile();
//
//			MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
//
//			parameters.add("file", new FileSystemResource(fileToSend));
//			HttpHeaders headers = new HttpHeaders();
//			headers.set("Content-Type", "multipart/form-data");
//			
//			HttpEntity<PostFileUploadDTO> httpEntity = new HttpEntity(parameters, headers);
//
//			try {
//				ResponseEntity<PostFileUploadDTO> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
//						PostFileUploadDTO.class);
//				return entity.getBody();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public List<String> saveFile(List<MultipartFile> fi) {

		try {
			String url = PostAPI.API_UPLOADFILE_POST;
			Path tempFile = Files.createTempFile(null, null);
			for (MultipartFile multipartFile : fi) {
				Files.write(tempFile, multipartFile.getBytes());
				File fileToSend = tempFile.toFile();
				MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();

				parameters.add("file", new FileSystemResource(fileToSend));
				HttpHeaders headers = new HttpHeaders();
				headers.set("Content-Type", "multipart/form-data");

				HttpEntity<String> httpEntity = new HttpEntity(parameters, headers);
				try {
					ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
							String.class);
					return (List<String>) entity;
				} catch (Exception e) {
//					e.printStackTrace();
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}

		return null;
	}
}
