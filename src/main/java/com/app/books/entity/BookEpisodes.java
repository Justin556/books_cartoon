package com.app.books.entity;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
//小说章节内容
public class BookEpisodes {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //小说ID
    private Integer bid;
    //章节标题
    private String title;
    //内容id
    private Integer jiNo;
    //上节编号
    private Integer beforeNo;
    //下节编号
    private Integer nextNo;
    //阅读需要的费用
    private BigDecimal money;
    //章节内容
    @Lob
    @Column(columnDefinition = "text")
    private String info;
}
