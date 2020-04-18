package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
//代理充值提现
public class AgentBalanceLog {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //代理用户id
    private Integer proxyId;
    //单号
    private String orderNo;
    //（1充值，0提现）
    private Integer orderType;
    //提现金额
    private BigDecimal money;
    //状态 0已分成 1未分成 2已取消
    private Integer status;
}
