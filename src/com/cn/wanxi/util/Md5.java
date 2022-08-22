package com.cn.wanxi.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * 如果两个方法没有明确的关系，那么应该将它提取到其他地方
 */
public class Md5 {

    /**
     * md5加密
     *
     * @param str
     * @return
     */
    public static String encoderByMd5(String str) {
        //确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            return Base64.encodeBase64String(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "C056Dl/oStNftflbnO6seQ==";
    }
}
