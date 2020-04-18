package com.app.books.entity;;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
//阅读记录
public class UserReadLog {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //用户id
    private Integer userId;

    //小说（漫画）id
    private Integer outId;

    //小说（漫画）章节id
    private Integer zjId;
}
