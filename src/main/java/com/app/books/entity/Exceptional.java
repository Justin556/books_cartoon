package com.app.books.entity;

import lombok.Data;

import java.util.Date;

/**
 * 打赏配置表
 */
@Data
public class Exceptional {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //打赏图片
    private String exceptionalImg;

    //打赏书币
    private String bookCurrency;

}
