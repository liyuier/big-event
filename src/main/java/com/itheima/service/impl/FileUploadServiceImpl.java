package com.itheima.service.impl;

import com.itheima.pojo.Result;
import com.itheima.pojo.TencentCosTmpSecret;
import com.itheima.service.FileUploadService;
import com.itheima.utils.TencentCosVisitUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${tencent.cos.secret-id}")
    String secretId;
    @Value("${tencent.cos.secret-key}")
    String secretKey;
    @Value("${tencent.cos.duration}")
    Integer durationSeconds;
    @Value("${tencent.cos.bucket.first.name}")
    String firstBucketName;
    @Value("${tencent.cos.bucket.first.region}")
    String firstBucketRegion;

    @Autowired
    TencentCosVisitUtil tencentCosVisitUtil;

    @Override
    public Result<String> upload(MultipartFile multipartFile) throws IOException {
        // 把文件内容储存到本地
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        String newFileName = "/image/" + UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        String eTag = uploadFileToTencentCos(multipartFile, newFileName);
        String newFilePath = "";
        if (eTag != null) {
            newFilePath = String.format("%s.cos.%s.myqcloud.com%s", firstBucketName, firstBucketRegion, newFileName);
            return Result.success(newFilePath);
        }
        return Result.error("上传失败！");
    }

    private String uploadFileToTencentCos(MultipartFile multipartFile, String newFileName) throws IOException {


        TencentCosTmpSecret tencentCosTmpSecret = tencentCosVisitUtil.tryGenTmpSecret();
        // 创建一个临时的文件
        File file = File.createTempFile("uploaded-", multipartFile.getOriginalFilename());
        // 将MultipartFile的内容写入到指定的File对象中
        multipartFile.transferTo(file);

        // 用户基本信息
        String tmpSecretId = tencentCosTmpSecret.getTmpSecretId();   // 替换为 STS 接口返回给您的临时 SecretId
        String tmpSecretKey = tencentCosTmpSecret.getTmpSecretKey();  // 替换为 STS 接口返回给您的临时 SecretKey
        String sessionToken = tencentCosTmpSecret.getSessionToken();  // 替换为 STS 接口返回给您的临时 Token


        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicSessionCredentials(tmpSecretId, tmpSecretKey, sessionToken);
        // 2 设置 bucket 区域,详情请参见 COS 地域 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(firstBucketRegion));
        clientConfig.setPrintShutdownStackTrace(false);
        // 3 生成 cos 客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket 名需包含 appid
        String bucketName = firstBucketName;

        // 上传 object, 建议 20M 以下的文件使用该接口
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newFileName, file);

        // 设置 x-cos-security-token header 字段
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setSecurityToken(sessionToken);
        putObjectRequest.setMetadata(objectMetadata);

        PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
        // 成功：putobjectResult 会返回文件的 etag
        String etag = putObjectResult.getETag();
        // 关闭客户端
        cosclient.shutdown();
        return etag;
    }
}
