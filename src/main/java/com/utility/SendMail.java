package com.utility;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SendMail {

    public static String send(String address) throws IOException {
        final String url = "http://api.sendcloud.net/apiv2/mail/send";
        final String apiUser = PropertiesUtil.getProperty("apiUser");
        final String apiKey =  PropertiesUtil.getProperty("apiKey");
        final String code = CoreUtils.randomString(6, true);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httPost = new HttpPost(url);

        List params = new ArrayList();
        // 您需要登录SendCloud创建API_USER，使用API_USER和API_KEY才可以进行邮件的发送。
        params.add(new BasicNameValuePair("apiUser", apiUser));
        params.add(new BasicNameValuePair("apiKey", apiKey));
        params.add(new BasicNameValuePair("from", "service@sendcloud.im"));
        params.add(new BasicNameValuePair("fromName", ""));
        params.add(new BasicNameValuePair("to", address));
        params.add(new BasicNameValuePair("subject", "来自赵立鼐的验证码"));
        params.add(new BasicNameValuePair("html", "验证码："+code));

        httPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        // 请求
        HttpResponse response = httpclient.execute(httPost);
        // 处理响应
        System.out.println(response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            // 读取xml文档
            String result = EntityUtils.toString(response.getEntity());
            httPost.releaseConnection();
            return code ;

        } else {
            System.err.println("error");
            httPost.releaseConnection();
            return "-1";
        }

    }


}

