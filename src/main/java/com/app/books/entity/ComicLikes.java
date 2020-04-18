package com.app.books.entity;;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//漫画点赞
public class ComicLikes {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //用户id
    private Integer userId;
    //漫画id
    private Integer comicId;
}
