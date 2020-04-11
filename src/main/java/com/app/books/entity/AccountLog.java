package com.app.books.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 用户书币
 */
@Data
@Entity
@Table(name = "t_account_log")
public class AccountLog extends BaseEntity {

    //用户编号
    private Integer userId;

    //账变类型
    private String consumptionType;

    //账变金额
    private BigDecimal amount;

    //描述
    private String descStr;
}
