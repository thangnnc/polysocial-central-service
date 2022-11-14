package com.polysocial.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


public class UploadToCloud {
    @Autowired 
    private Cloudinary cloudinary;

    public String saveFile(MultipartFile file) throws IOException {
        System.out.println("join");
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
            System.out.println(fi.getOriginalFilename());
            File file = new File("Files/" + fi.getOriginalFilename());
            System.out.println(file);
			String json = "" + this.cloudinary.uploader().upload(file,
					ObjectUtils.asMap("resource_type", "auto"));
                    System.out.println("done");
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
}
