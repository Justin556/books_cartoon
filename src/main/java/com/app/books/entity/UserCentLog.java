package com.app.books.entity;;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户分佣记录
 */
@Data
public class UserCentLog {
    private Integer id;

    //日期格式
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Date updateTime;

    //获拥用户id
    private Integer userId;

    //获拥用户名
    private String userName;

    //提供佣金用户id
    private Integer outUserId;

    //提供佣金用户级别
    private String outUserLevel;

    //分佣比例
    private Float outUserLevelScale;

    //提供佣金用户名
    private String outUserName;

    //来源充值单号
    private String orderNo;

    //单金额
    private BigDecimal orderFee;

    //分佣金额
    private BigDecimal commission;

    //0 未分成  1  已分成  2已取消
    private Integer status;

    //*********************子查询字段***********************
    //提供佣金的用户是否vip
    private Integer isVip;

    //提供佣金的用户头像
    private String portrait;

    //提供佣金数量
    private BigDecimal tiCommission;
}
