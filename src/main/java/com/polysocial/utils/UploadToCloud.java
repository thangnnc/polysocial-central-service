package com.polysocial.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


public class UploadToCloud {
    @Autowired 
    private Cloudinary cloudinary;

    public String saveFile(MultipartFile file) throws IOException {
		String url = "";
		File folder = new File("Files");
		try {
			folder.mkdir();
			saveLocal(file);
			url = upLoadServer(file);
			deleteFile();
		} catch (Exception e) {
			folder.delete();
			e.printStackTrace();
			return url;
		}
		return url;
	}

	public void saveLocal(MultipartFile fi) throws IOException {
		FileUploadUtil.saveFile(fi.getOriginalFilename(), fi);
	}

	public String upLoadServer(MultipartFile fi) {
		int firtsIndex = 0;
		int lastIndex = 0;
		String url = "";
		try {
            File file = new File("Files/" + fi.getOriginalFilename());
			String json = "" + this.cloudinary.uploader().upload(file,
					ObjectUtils.asMap("resource_type", "auto"));
			firtsIndex = json.indexOf("url=");
			lastIndex = json.indexOf("created_at");
			url = json.substring(firtsIndex + 4, lastIndex - 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}

	void deleteFile() {
		Path filePath = Paths.get("Files");
		if (filePath.toFile().exists()) {
			File[] files = filePath.toFile().listFiles();
			for (File file : files) {
				file.delete();
			}
		}
	}

	// post
    String url = "";
    String type = "";

	public List<String> saveFilePost(List<MultipartFile> fi) throws IOException {
        List<String> urlPath = new ArrayList();
        File folder = new File("Files");
        try {
            folder.mkdir();
            saveLocalPost(fi);
            urlPath = upLoadServerPost(fi);
            deleteFile();
        } catch (Exception e) {
            this.url = "";
            this.type = "";
            folder.delete();
            return urlPath;
        }
        return urlPath;
    }



    public void saveLocalPost(List<MultipartFile> fi) throws IOException {
        for (int i = 0; i < fi.size(); i++) {
            FileUploadUtil.saveFile(fi.get(i).getOriginalFilename(), fi.get(i));
        }
    }


	public List<String> upLoadServerPost(List<MultipartFile> fi) {
        List<String> urlPath = new ArrayList<>();
        String type = ""; // check type luc up len server
        int firtsIndex = 0;
        int lastIndex = 0;
        String url = "";
        for (int i = 0; i < fi.size(); i++) {
            type = fi.get(0).getContentType(); // check type luc up len server
            String fileName = fi.get(i).getOriginalFilename();
            try {
                if (type.equals("jpg") || type.equals("png") || type.equals("jpeg")) {
                    String json = "" + this.cloudinary.uploader().upload("./Files/" + fileName,
                            ObjectUtils.asMap("moderation", "aws_rek"));
                        System.out.println(json);
                    firtsIndex = json.indexOf("url=");
                    lastIndex = json.indexOf("created_at");
                    url = json.substring(firtsIndex + 4, lastIndex - 2);
                    urlPath.add(url); // lay ra duong dan anh
                    // cac loai anh bi cam
                    String[] typeImage = { "Explicit Nudity", "Suggestive", "Violence", "Visually Disturbing",
                            "Rude Gesture", "Drugs", "Tobacco", "Alcohol", "Gambling", "Hate Symbols" };
                    for (int j = 0; j < type.length(); j++) {
                        if (json.contains(typeImage[j])) {
                            return null;
                        }
                    }
                } else {
                    String json = "" + this.cloudinary.uploader().upload("./Files/" + fileName,
                            ObjectUtils.asMap("resource_type", "auto"));
                            System.out.println(json);
                    if (type.equals("video")) {
                        firtsIndex = json.lastIndexOf("url=");
                        lastIndex = json.indexOf("tags=");
                        url = json.substring(firtsIndex + 4, lastIndex - 2);
                        String types = url.substring(url.lastIndexOf(".") + 1);
                        this.type = types;
                        this.url = url;
                    } else {
                        firtsIndex = json.indexOf("url=");
                        lastIndex = json.indexOf("created_at");
                        url = json.substring(firtsIndex + 4, lastIndex - 2);
                        String types = url.substring(url.lastIndexOf(".") + 1);
                        this.type = types;
                        this.url = url;
                    }
                    urlPath.add(url); // lay ra duong dan anh
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return urlPath;
    }
}
