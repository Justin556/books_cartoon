package com.app.books.entity;

import lombok.Data;

import java.util.Date;

@Data
/**
 * 反馈建议
 */
public class Suggest {
    private Date createTime;
    //反馈建议信息
    private String msg;
    //图片存储路径
    private String path;
    //电话
    private String tel;
    //用户名
    private String userName;
    //用户id
    private Integer userId;
}
