package com.lee.video.entity.xs;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 打赏配置表
 */
@Data
@Entity
@Table(name = "t_exceptional_config")
public class Exceptional extends BaseEntity {

    //打赏图片
    private String exceptionalImg;

    //打赏书币
    private String bookCurrency;

}
