package com.lee.video.entity.xs;;

import com.lee.video.entity.xs.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 充值配置表
 */
@Data
@Entity
@Table(name = "t_recharge_config")
public class Recharge extends BaseEntity {

    //充值数
    private Integer rechargeNum;

    //赠送数
    private Integer givingNum;
}
