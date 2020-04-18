package com.app.books.entity;;

import lombok.Data;

import java.util.Date;


/**
 * 用户分销配置表
 */
@Data
public class RetailStore {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //分销名称
    private String retailStoreName;

    //分成模式（0按订单，1按金额）
    private Integer divided;

    //一级分销
    private String levelOne;

    //一级比例
    private Integer levelOneScale;

    private String levelTwo;

    private Integer levelTwoScale;

    private String levelThree;

    private Integer levelThreeScale;
}
