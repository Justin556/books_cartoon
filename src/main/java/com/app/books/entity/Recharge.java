package com.app.books.entity;;

import lombok.Data;

import java.util.Date;


/**
 * 充值配置表
 */
@Data
public class Recharge {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //充值数
    private Integer rechargeNum;

    //赠送数
    private Integer givingNum;
}
