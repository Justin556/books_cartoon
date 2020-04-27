package com.app.books.entity;;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;


@Getter
@Setter
//小说收藏
public class BookCollect {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //小说ID
    private Integer bid;
    //收藏章节
    private Integer readNumber;
    //用户ID
    private Integer userId;
    //小说标题
    private String title;
    //封面图(列表)
    private String coverPic;
    //作品简介
    private String summary;

    public BookCollect() {
    }

    public BookCollect(Date createTime, Integer userId, Integer bid, String title, String coverPic, String summary) {
        this.createTime = createTime;
        this.bid = bid;
        this.userId = userId;
        this.title = title;
        this.coverPic = coverPic;
        this.summary = summary;
    }
}
