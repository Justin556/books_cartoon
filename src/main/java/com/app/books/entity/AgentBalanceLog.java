package com.lee.video.entity.xs;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "t_agent_balance_log")
//代理充值提现
public class AgentBalanceLog extends BaseEntity {
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
