package com.fileStore.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fileStore.service.fileService;


@RestController
@RequestMapping("/file")
public class ImageController {

    @Autowired
    private  fileService fileService;

    @PostMapping("/")
    public String saveImage(@RequestParam("file") MultipartFile file){
        try {
            String imageName =  fileService.saveImage(file);
            return imageName;
        }catch( IOException e){
            return "Error";
        }
    }   
}
