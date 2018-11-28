package com.utility;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class CoreUtils {
    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String randomString(int length, boolean isNumeric) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        if (isNumeric) {
            base = "0123456789";
        }

        Random random = new Random();
        StringBuffer buffer = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            buffer.append(base.charAt(random.nextInt(base.length())));
        }

        return buffer.toString();
    }

    /**
     * 不重复的参数进行拼装，返回查询条件字符串
     *
     * @param parameters 参数map
     * @param sort       是否按照字典排序
     * @return
     */
    public static String generateQueryString(Map<String, Object> parameters, boolean sort) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            // log.debug("参数：{}", entry.getKey());
            if (!"".equals(entry.getValue())) {
                list.add(entry.getKey() + "=" + entry.getValue());
            }
        }

        String[] arrayToSort = list.toArray(new String[list.size()]);
        if (sort) {
            Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            buffer.append(arrayToSort[i]);
            if (i < (list.size() - 1)) {
                buffer.append("&");
            }
        }
        return buffer.toString();
    }

    /**
     * 根据参数获得相关签名
     *
     * @param toUpperCase  加密参数，ASCII 码从小到大排序（字典序）
     * @param encrypt 加密方式 SHA1 MD5
     * @return
     */
    public static String signature(String buffer, String encrypt, boolean toUpperCase) {
        String sign = "";

        if ("MD5".equals(encrypt)) {
            // MD5加密
            sign = Hashing.md5().hashString(buffer, Charsets.UTF_8).toString();
        } else if ("SHA1".equals(encrypt)) {
            // SHA1加密
            sign = Hashing.sha1().hashString(buffer, Charsets.UTF_8).toString();
        }

        if (toUpperCase) {
            sign = sign.toUpperCase();
        }

        return sign;
    }

    /**
     * 根据参数获得相关签名
     *
     * @param params  加密参数，ASCII 码从小到大排序（字典序）
     * @param encrypt 加密方式 SHA1 MD5
     * @return
     */
    public static String signature(Map params, String encrypt, boolean toUpperCase) {
        String sign = "";
        // 拼接字符串，按照字典排序
        String buffer = generateQueryString(params, true);

        // log.debug("待加密的字符串 => {}", buffer.toString());
        if ("MD5".equals(encrypt)) {
            // MD5加密
            sign = Hashing.md5().hashString(buffer, Charsets.UTF_8).toString();
        } else if ("SHA1".equals(encrypt)) {
            // SHA1加密
            sign = Hashing.sha1().hashString(buffer, Charsets.UTF_8).toString();
        }
        // log.debug("加密后的字符串 <=> {}", sign);

        if (toUpperCase) {
            sign = sign.toUpperCase();
        }

        return sign;
    }

}
