package com.app.books.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 用户提现，充值
 */
@Data
@Entity
@Table(name = "t_user_balance_log")
public class UserBalanceLog extends BaseEntity {

    //用户编号
    private Integer userId;

    //用户名
    private String userName;

    //单编号
    private String orderNo;

    //单类型（1充值，0提现）
    private String orderType;

    //单金额
    private BigDecimal orderFee;

    //0 未操作  1  已操作
    private Integer status;
}
