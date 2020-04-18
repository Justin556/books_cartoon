package com.app.books.entity;

import lombok.Data;

import java.util.Date;


/**
 * 支付配置表
 */
@Data
public class PayConfig {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private String payCompanyName;

    private String companyNo;

    private String companyKey;
}
