package com.app.books.entity;;

import lombok.Data;

import java.util.Date;


/**
 * 用户书币变动表
 */
@Data
public class UserCurrencyLog {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //用户编号
    private Integer userId;

    //用户名
    private String userName;

    //本次书币变动
    private Integer currency;

    //书币变动类型（参考账变变动记录下拉选项）
    private Integer currencyType;

    //书币变动来源与下游分销的哪个用户id
    private String otherUserId;
}
