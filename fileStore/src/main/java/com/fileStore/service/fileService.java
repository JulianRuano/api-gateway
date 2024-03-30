package com.fileStore.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class fileService {

    private static final String UPLOAD_DIR = "/mnt/image_volume/";

    public String saveImage(MultipartFile file ) throws IOException {
        String imageName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File convertedFile = new File(UPLOAD_DIR + imageName);
        convertedFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return imageName;
    }
}
