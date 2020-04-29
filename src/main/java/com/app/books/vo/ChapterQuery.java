package com.app.books.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 漫画列表查询条件
 */
@Getter
@Setter
public class ChapterQuery {

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
    //创建时间
    private String createTime;
    //以下为子查询所用
    private String title;
    private String coverPic;
}
