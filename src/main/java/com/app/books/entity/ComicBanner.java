package com.app.books.entity;;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
//漫画轮播图
public class ComicBanner {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //图片路径
    private String pic;
    //漫画ID
    private Integer comicId;
}
