package com.app.books.entity;;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;


@Getter
@Setter
//书或漫画评论
public class Comment {


    private Integer id;

    private String createTime;

    private Date updateTime;
    //书（漫画）id
    private Integer outId;
    //评论
    private String commentInfo;
    //作者
    private Integer userId;
    //书（漫画）章节id
    private Integer zjId;

    //1小说2漫画
    private Integer type;
    //----------------子查询字段--------------------
    //用户名
    private String userName;

    //头像
    private String portrait;

    public Comment(Date createTime, Integer outId, String commentInfo, Integer userId) {
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        this.createTime = sdf.format(createTime);
        this.outId = outId;
        this.commentInfo = commentInfo;
        this.userId = userId;
    }

    public Comment() {
    }
}
