package com.example.testproject.controller;

import com.example.testproject.model.Result;
import com.example.testproject.utils.Cloud;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@RestController
public class UploadController {

    @Autowired
    private Cloud cloud;
   //本地上传
   /*@PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传： {}，{}，{}", username, age, image);
        String orginalName = image.getOriginalFilename();
        int idex = orginalName.lastIndexOf(".");
        String extension = orginalName.substring(idex);
        String newFileName = System.currentTimeMillis() + extension;
        return Result.success();
    }*/
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传： {}", image.getOriginalFilename());
        String url = cloud.upload(image);
        return Result.success(url);
    }
}
