package com.app.books.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户书币
 */
@Data
public class AccountLog {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //用户编号
    private Integer userId;

    //账变类型
    private String consumptionType;

    //账变金额
    private BigDecimal amount;

    //描述
    private String descStr;
}
