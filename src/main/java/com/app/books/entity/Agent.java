package com.lee.video.entity.xs;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "t_agent")
//代理
public class Agent extends BaseEntity {
    //代理名称
    private String name;
    //代理昵称
    private String proxyNickName;
    //登录密码
    private String password;
    //代理手机号
    private String phone;
    //支付宝名称
    private String aliName;
    //支付宝账号
    private String aliAccount;
    //分成比例
    private float separate;
    //扣除比例
    private float ratio;
    //代理推广二维码
    private String gqrCode;
    //余额
    private BigDecimal balance;
    //审核状态
    private Integer status;
}
