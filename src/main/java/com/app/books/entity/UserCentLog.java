package com.app.books.entity;;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户分佣记录
 */
@Data
public class UserCentLog {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //获拥用户id
    private Integer userId;

    //用户名
    private String userName;

    //提供佣金用户id
    private Integer outUserId;

    //提供佣金用户级别
    private Integer outUserLevel;

    //提供佣金用户名
    private String outUserName;

    //来源充值单号
    private String orderNo;

    //单金额
    private BigDecimal orderFee;

    //分佣金额
    private BigDecimal commission;

    //0 未分成  1  已分成  2已取消
    private Integer status;
}
