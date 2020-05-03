package com.app.books.entity;;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
//客服消息
public class Article {
    private Integer id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Date updateTime;
    //标题
    private String title;
    //作者
    private String author;
    //排序
    private Integer sort;
    //封面图片
    private String cover;
    //封面图是否显示在正文
    private Integer showCover;
    //正文内容
    private String body;
}
