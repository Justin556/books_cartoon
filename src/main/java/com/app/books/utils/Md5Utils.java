package com.app.books.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    private static Logger logger = LoggerFactory.getLogger(Md5Utils.class);

    public static String MD5(String content) {
        if (!StringUtils.isEmpty(content)) {
            try {
                return HexUtil.byte2hex(MessageDigest.getInstance("md5").digest(content.getBytes()));
            } catch (NoSuchAlgorithmException e) {
                logger.error("MD5加密错误！" + e.getMessage());
            }
        } else {
            logger.error("MD5加密内容为空！");
        }
        return null;
    }

    public static String SHA(String content) {
        if (!StringUtils.isEmpty(content)) {
            try {
                return HexUtil.byte2hex(MessageDigest.getInstance("SHA").digest(content.getBytes()));
            } catch (NoSuchAlgorithmException e) {
                logger.error("SHA加密错误！" + e.getMessage());
                throw new RuntimeException("SHA加密错误！" + e.getMessage());
            }
        } else {
            logger.error("SHA加密内容为空！");
        }
        return null;
    }
}
