package com.app.books.entity;;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//小说点赞
public class BookLikes {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //用户id
    private Integer userId;
    //小说id
    private Integer bid;
    //状态：（0：已取消； 1：已点赞）
    private Integer status;
}
