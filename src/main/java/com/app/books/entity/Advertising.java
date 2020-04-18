package com.lee.video.entity.xs;

import com.lee.video.entity.xs.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 广告配置表
 */
@Data
@Entity
@Table(name = "t_advertising_config")
public class Advertising extends BaseEntity {

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
