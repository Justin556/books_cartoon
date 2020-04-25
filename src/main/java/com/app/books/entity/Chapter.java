package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Chapter {
    //用户id
    private Integer userId;
    //当前观看章节id
    private Integer chapterId;
    //当前观看章节名称
    private String chapter;
    //小说或者漫画id
    private Integer outId;
    //1小说 2漫画
    private Integer type;

    private Date createTime;
}
