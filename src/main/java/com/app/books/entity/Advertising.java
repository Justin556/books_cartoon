package com.app.books.entity;

import lombok.Data;

import java.util.Date;

/**
 * 广告配置表
 */
@Data
public class Advertising {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //是否开启
    private Integer isOpen;

    //广告链接
    private String advertisingUrl;

    //广告图片
    private String advertisingImg;

    //小说编号（不指定则全局有效）
    private Integer bid;

    //展示章节（可多选）
    private String showChapter;

    //章节倍数展示
    private Integer showMultiple;
}
