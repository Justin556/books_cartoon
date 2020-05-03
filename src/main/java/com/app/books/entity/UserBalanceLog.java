package com.app.books.entity;;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户提现，充值
 */
@Data
public class UserBalanceLog {
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Date updateTime;

    //用户编号
    private Integer userId;

    //用户名
    private String userName;

    //单编号
    private String orderNo;

    //单类型（1充值, 0提现）
    private String orderType;

    //单金额
    private BigDecimal orderFee;

    //0 未操作  1  已操作
    private Integer status;
}
