package com.app.books.controller;


import com.app.books.config.AuthenticationInterceptor;
import com.app.books.mapper.UserMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * Created by jack on 2017/10/30.
 */
@RestController
@RequestMapping("/uploadDownload")
public class UploadDownloadController {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UploadDownloadController.class);

    private String uploadDir="/home/project/picture/touxiang";

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public JSONObject uploadImage(@RequestParam(value = "file") MultipartFile file) throws RuntimeException {

        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = uploadDir;
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            userMapper.upload("/books/touxiang/"+fileName,Integer.parseInt(authenticationInterceptor.userId));
            logger.info("上传成功后的文件路径未：" + filePath + fileName);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}