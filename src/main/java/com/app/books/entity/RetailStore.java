package com.lee.video.entity.xs;;

import com.lee.video.entity.xs.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户分销配置表
 */
@Data
@Entity
@Table(name = "t_retail_store")
public class RetailStore extends BaseEntity {

    //分销名称
    private String retailStoreName;

    //分成模式（0按订单，1按金额）
    private Integer divided;

    //一级分销
    private String levelOne;

    //一级比例
    private Integer levelOneScale;

    private String levelTwo;

    private Integer levelTwoScale;

    private String levelThree;

    private Integer levelThreeScale;
}
