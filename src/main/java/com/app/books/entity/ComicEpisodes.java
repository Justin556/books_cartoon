package com.app.books.entity;;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
//漫画分集内容
public class ComicEpisodes {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //漫画ID
    private Integer comicId;
    //分集标题
    private String title;
    //集数编号
    private Integer jiNo;
    @Lob
    @Column(columnDefinition="text")
    private String pics;
    //上节编号
    private Integer beforeNo;
    //下节编号
    private Integer nextNo;
    //阅读需要的费用
    private BigDecimal money;
}
