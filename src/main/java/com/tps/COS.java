package com.tps;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;

import com.qcloud.cos.model.PutObjectRequest;

import com.qcloud.cos.region.Region;
import com.utility.PropertiesUtil;

import org.springframework.web.multipart.MultipartFile;


import java.io.File;

public class COS {

    public static String tps(MultipartFile cosFile) throws Exception {
        String secretId = PropertiesUtil.getProperty("secretId");
        String secretKey = PropertiesUtil.getProperty("secretKey");
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照
        // https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-chengdu"));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        String bucketName = "znn-1257394013";
        String key = System.currentTimeMillis() + cosFile.getOriginalFilename();
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        // 指定要上传到 COS 上的路径
//        byte[] buffer=cosFile.getBytes();
//        File savefile = new File(new File(savePath), newFileName);
//
//        multipartFile.transferTo(savefile);

        File savefile = new File(new File("D:"), "file");
        cosFile.transferTo(savefile);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, savefile);
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
//        Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
//        String url = cosClient.generatePresignedUrl(bucketName, key, expiration).toString();
//        https://znn-1257394013.cos.ap-chengdu.myqcloud.com/kang.jpg
        return "https://" + bucketName + ".cos.ap-chengdu.myqcloud.com/" + key;
    }
}
