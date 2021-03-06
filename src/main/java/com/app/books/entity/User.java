package com.app.books.entity;;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户表
 */
@Data
public class User {
    private Integer id;

    private String createTime;

    private Date updateTime;

    //用户来源（0来源与代理，1来源分销，后续可能来源棋牌游戏等）
    private Integer userSource;

    //如果来源代理则有值，默认不来源
    private Integer proxyId;

    //是否是vip (0:否  1：是)
    private Integer isVip = 0;

    //vip开始时间
    private Date vipStartTime;

    //vip到期时间
    private Date vipEndTime;

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

    //签到将赠送的书币(冗余字段)
    private Integer signCurrency;

    //用户的分销总佣金(冗余字段)
    private BigDecimal commission;

    //vip开始时间
    private String startTime;

    //vip到期时间
    private String endTime;

    //子查询所用字段
    //统计当前总佣金
    private BigDecimal  tiCommission;


    public void setCreateTime(Date createTime){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTime=sdf.format(createTime);
    }
}
