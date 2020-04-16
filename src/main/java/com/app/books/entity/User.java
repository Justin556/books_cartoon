package com.app.books.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 用户表
 */
@Data
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

    //用户id
    private Integer id;

    //用户来源（0来源与代理，1来源分销，后续可能来源棋牌游戏等）
    private Integer userSource;

    //如果来源代理则有值，默认不来源
    private Integer proxyId = 0;

    //是否是vip
    private Integer isVip = 0;

    //用户名称
    private String userName;

    //用户编号
    private String userNo;

    //支付宝名称
    private String aliName;

    //支付宝账号
    private String aliAccount;

    //余额
    private BigDecimal balance;

    //书币
    private Integer bookCurrency;
}
