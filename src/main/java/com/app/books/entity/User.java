package com.app.books.entity;;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表
 */
@Data
public class User {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //用户来源（0来源与代理，1来源分销，后续可能来源棋牌游戏等）
    private Integer userSource;

    //如果来源代理则有值，默认不来源
    private Integer proxyId = 0;

    //是否是vip
    private Integer isVip = 0;

    //用户名称
    private String userName;

    //密码
    private String password;

    //用户编号(弃用)
    private String userNo;

    //支付宝名称
    private String aliName;

    //支付宝账号
    private String aliAccount;

    //余额
    private BigDecimal balance;

    //书币
    private Integer bookCurrency;

    //今日是否已签到(0：未签到； 1：已签到) （冗余字段）
    private Integer isSignIn;

    //头像
    private String portrait;
}
