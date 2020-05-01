package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
//代理佣金分成记录
public class AgentShareLog {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //代理用户id
    private Integer proxyId;
    //单号
    private String orderNo;
    //充值金额
    private BigDecimal cMoney;
    //分佣金额
    private BigDecimal fMoney;
    //分成的用户来源
    private Integer userId;
    //状态 0已分成 1未分成 2已取消
    private Integer status;
}
