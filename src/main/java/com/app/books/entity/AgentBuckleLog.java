package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "t_agent_buckle_log")
//代理扣量记录
public class AgentBuckleLog extends BaseEntity {
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
