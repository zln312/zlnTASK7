package com.tps;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketReferer;
import com.utility.PropertiesUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Oss {

    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = PropertiesUtil.getProperty("accessKeyId");
    private static String accessKeySecret = PropertiesUtil.getProperty("accessKeySecret");
    private static OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);


    public static String tps(MultipartFile uploadFile) throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。

        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。

        String key = System.currentTimeMillis() + uploadFile.getOriginalFilename();
        // 创建OSSClient实例。
        List<String> refererList = new ArrayList<>();
// 添加Referer白名单。Referer参数支持通配符星号（*）和问号（？）。
        refererList.add("http://192.168.2.104:8080/*");

// 设置存储空间Referer列表。设为true表示Referer字段允许为空。
        BucketReferer br = new BucketReferer(true, refererList);
        String bucketName = "zhaolinai";
        ossClient.setBucketReferer(bucketName, br);
// 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        ossClient.putObject(bucketName, key, new ByteArrayInputStream(uploadFile.getBytes()));

        // 关闭OSSClient。
        ossClient.shutdown();
        return "http://zhaolinai.oss-cn-beijing.aliyuncs.com/"+key;
    }


}


