package com.app.books.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 支付配置表
 */
@Data
@Entity
@Table(name = "t_pay_config")
public class PayConfig extends BaseEntity {

    private String payCompanyName;

    private String companyNo;

    private String companyKey;
}
