package com.move;

import com.aliyun.oss.OSSClient;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectListing;
import com.qcloud.cos.region.Region;
import com.utility.PropertiesUtil;

import java.io.File;
import java.util.List;

public class CO {
    //将腾讯云的图片迁移到阿里云
    private static void move() {
        String secretId = PropertiesUtil.getProperty("secretId");
        String secretKey = PropertiesUtil.getProperty("secretKey");
        String cosBucketName = "znn-1257394013";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 设置bucket的区域, COS地域的简称请参照
        // https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-chengdu"));
        //  生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        ObjectListing objectListing = cosClient.listObjects(cosBucketName);
        List<COSObjectSummary> list = objectListing.getObjectSummaries();
        for (COSObjectSummary s : list) {
            String objectName = s.getKey();
            cosClient.getObject(new GetObjectRequest(cosBucketName, objectName), new File("E:\\TPS\\" + objectName));
        }
        cosClient.shutdown();


        //阿里云对象存储账号信息
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        String accessKeyId = PropertiesUtil.getProperty("accessKeyId");
        String accessKeySecret = PropertiesUtil.getProperty("accessKeySecret");

        String ossBucketName = "zlnbox";

        //创建OSSClient客户端实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        for (COSObjectSummary s : list) {
            String objectName = s.getKey();
            File file = new File("E:\\TPS" + objectName);
            ossClient.putObject(ossBucketName, ossBucketName, file);
        }
        ossClient.shutdown();
    }


    public static void main(String[] args) {
        CO.move();
    }
}
