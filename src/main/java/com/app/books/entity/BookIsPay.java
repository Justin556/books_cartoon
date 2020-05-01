package com.app.books.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BookIsPay {
    private Integer id;

    private Date createTime;
    //用户id
    private Integer userId;
    //章节id
    private Integer chapterId;
    //是否已付费（0：否   1：是）
    private Integer isPay;

    public BookIsPay() {
    }

    public BookIsPay(Integer userId, Integer chapterId, Integer isPay) {
        this.userId = userId;
        this.chapterId = chapterId;
        this.isPay = isPay;
    }
}
