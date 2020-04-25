package com.app.books.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BookHistory {
    private Integer id;

    private Date createTime;
    //小说id
    private Integer bid;
    //小说章节id
    private Integer jiNo;
    //用户id
    private Integer userId;

    public BookHistory() {
    }

    public BookHistory(Date createTime, Integer bid, Integer jiNo, Integer userId) {
        this.createTime = createTime;
        this.bid = bid;
        this.jiNo = jiNo;
        this.userId = userId;
    }
}
