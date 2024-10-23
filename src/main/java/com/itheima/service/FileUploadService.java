package com.itheima.service;

import com.itheima.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Title: FileUploadService
 * @Author yuier
 * @Package com.itheima.service
 * @Date 2024/10/23 23:43
 * @description: 文件上传接口 service
 */

public interface FileUploadService {
    Result<String> upload(MultipartFile file) throws IOException;
}
