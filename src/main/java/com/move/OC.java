package com.move;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.utility.PropertiesUtil;

import java.io.*;
import java.util.List;

public class OC {

    //将阿里云存储的数据迁移至腾讯云存储
    private static void move() {

        //阿里云对象存储账号信息
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = PropertiesUtil.getProperty("accessKeyId");
        String accessKeySecret = PropertiesUtil.getProperty("accessKeySecret");
        String ossBucketName = "zlnbox";

        //创建OSSClient客户端实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);


        ObjectListing objectListing = ossClient.listObjects(ossBucketName);
        List<OSSObjectSummary> list = objectListing.getObjectSummaries();
        for (OSSObjectSummary o : list) {
            String objectName = o.getKey();
            ossClient.putObject(ossBucketName, objectName, new File("E//TPS//" + objectName));

        }
        //关闭ossClient
        ossClient.shutdown();


        //腾讯云对象存储账号信息
        String secretId = PropertiesUtil.getProperty("secretId");
        String secretKey = PropertiesUtil.getProperty("secretKey");
        String bucketName2 = "znn-1257394013";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        //设置bucket的区域
        ClientConfig clientConfig = new ClientConfig(new Region("ap-chengdu"));
        //生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);

        //遍历数组
        for (OSSObjectSummary c : list) {
            String objectName = c.getKey();
            File file = new File("E:\\TPS\\" + objectName);
            //上传文件
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName2, objectName, file);
            cosClient.putObject(putObjectRequest);
        }
        //关闭cosClient
        cosClient.shutdown();
    }


    public static void main(String[] args) {
        OC.move();
    }
}