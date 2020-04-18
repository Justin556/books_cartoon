package com.app.books.entity;;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
//书或漫画评论
public class Comment {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //书（漫画）id
    private Integer outId;
    //评论
    private String commentInfo;
    //作者
    private Integer userId;
    //书（漫画）章节id
    private Integer zjId;

}
