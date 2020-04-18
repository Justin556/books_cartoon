package com.app.books.entity;;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//漫画收藏
public class ComicCollect {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //漫画ID
    private Integer comicId;
    //当前阅读章节
    private Integer readNumber;
    //用户ID
    private Integer userId;
    //漫画标题
    private String title;
    //封面图(列表)
    private String coverPic;
}
