package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
//代理扣量记录
public class AgentBuckleLog {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //代理用户id
    private Integer proxyId;
    //单号
    private String orderNo;
    //扣量的用户来源
    private Integer userId;
    //充值金额
    private BigDecimal cMoney;
    //扣量金额
    private BigDecimal kMoney;
}
