package com.djm.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.djm.oss.ConstantPropertiesUtil;
import com.djm.oss.service.ossservice;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


/**
 * @author djm
 * @create 2021-09-19 16:06
 */
@Service
public class ossserviceimpl implements ossservice {
    String endpoint =ConstantPropertiesUtil.ENDPOINT;
    String keyId =ConstantPropertiesUtil.ACCESS_KEY_ID;
    String keySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
    String bucketName =ConstantPropertiesUtil.BUCKET_NAME;
    String uploadUrl = null;
    @Override
    public String upload(MultipartFile file) {
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSS ossClient = new OSSClientBuilder().build(endpoint,keyId, keySecret);

            //获取上传文件流
            InputStream inputStream = file.getInputStream();

            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString().replaceAll("-","");
            String fileUrl = filePath +"/" + fileName +original;

            //文件上传至阿里云
            ossClient.putObject(bucketName, fileUrl, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //获取url地址
            uploadUrl = "https://" + bucketName + "." + endpoint + "/" + fileUrl;
            return uploadUrl;

        } catch (IOException e) {
        }

        return uploadUrl;
    }
}
