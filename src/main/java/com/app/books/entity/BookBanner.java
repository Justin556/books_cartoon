package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//小说轮播图
public class BookBanner {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //图片路径
    private String pic;
    //小说ID
    private Integer bid;
}
