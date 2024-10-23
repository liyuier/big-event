package com.itheima.service.impl;

import com.itheima.pojo.Result;
import com.itheima.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Title: FileUploadServiceImpl
 * @Author yuier
 * @Package com.itheima.service.impl
 * @Date 2024/10/23 23:43
 * @description: 文件上传业务 service 实现类
 */

@Service
public class  FileUploadServiceImpl implements FileUploadService {

    @Override
    public Result<String> upload(MultipartFile file) throws IOException {
        // 把文件内容储存到本地
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("F:\\Codes\\Learn\\Java\\Heima_spring+vue\\Code\\files\\" + newFileName));
        return Result.success("url...");
    }
}
