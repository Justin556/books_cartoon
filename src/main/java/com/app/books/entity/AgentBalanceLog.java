package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
//代理下面的用户充值记录
public class AgentBalanceLog {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //代理用户id
    private Integer proxyId;
    //单号
    private String orderNo;
    //充值金额
    private BigDecimal money;
    //状态（0：成功； 1：失败）
    private Integer status;
}
