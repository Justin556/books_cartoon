package com.app.books.entity;;

import lombok.Data;

import java.util.Date;

/**
 * 用户打赏
 */
@Data
public class UserSendLog {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //用户id
    private Integer userId;

    //打赏书币（金额）类型（0书币，1金额）
    private Integer sendType;

    //打赏小说或漫画id
    private Integer outId;

    //打赏金额
    private Integer amount;


    //打赏小说或漫画的某个章节id
    private Integer zjId;

    //----------------子查询字段--------------------
    //用户名
    private String userName;

    //打赏总金额
    private Integer  userAmount;
}
