package com.lee.video.entity.xs;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "t_agent_share_log")
//代理佣金分成记录
public class AgentShareLog extends BaseEntity {
    //代理用户id
    private Integer proxyId;
    //单号
    private String orderNo;
    //金额
    private BigDecimal money;
    //分成的用户来源
    private Integer userId;
    //状态 0已分成 1未分成 2已取消
    private Integer status;
}
