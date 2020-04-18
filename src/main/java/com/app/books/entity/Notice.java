package com.app.books.entity;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
//公告
public class Notice {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //标题
    private String title;
    //内容
    @Lob
    @Column(columnDefinition="text")
    private String content;
}
