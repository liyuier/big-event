package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Title: FileUploadController
 * @Author yuier
 * @Package com.itheima.controller
 * @Date 2024/10/23 23:41
 * @description: 文件上传 controller
 */

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping
    public Result<String> upload(MultipartFile file) throws IOException {
        return fileUploadService.upload(file);
    }
}
