package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
//代理
public class Agent {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //代理名称
    private String name;
    //代理昵称
    private String proxyNickName;
    //登录密码
    private String password;
    //代理手机号
    private String phone;
    //支付宝名称
    private String aliName;
    //支付宝账号
    private String aliAccount;
    //分成比例
    private float separate;
    //扣除比例
    private float ratio;
    //代理推广二维码
    private String gqrCode;
    //余额
    private BigDecimal balance;
    //审核状态
    private Integer status;
}
