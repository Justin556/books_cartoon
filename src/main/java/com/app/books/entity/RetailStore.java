package com.app.books.entity;;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * 用户分销配置表
 */
@Data
@Getter
@Setter
public class RetailStore {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //分销名称
    private String retailStoreName;

    //分成模式（0按订单，1按金额）(目前作废，后续待定)
    private Integer divided;

    //一级分销
    private String levelOne;

    //一级比例
    private Float levelOneScale;

    private String levelTwo;

    private Float levelTwoScale;

    private String levelThree;

    private Float levelThreeScale;
}
