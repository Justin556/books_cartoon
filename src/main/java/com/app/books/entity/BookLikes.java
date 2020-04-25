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

    public BookLikes() {
    }

    public BookLikes(Date createTime, Integer userId, Integer bid) {
        this.createTime = createTime;
        this.userId = userId;
        this.bid = bid;
    }
}
