package com.app.books.entity;;
import lombok.Getter;
import lombok.Setter;

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
    //状态：（0：已取消； 1：已收藏）
    private Integer status;
}
