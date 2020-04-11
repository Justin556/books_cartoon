package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_book")
public class Book extends BaseEntity {
    //书名
    private String title;
    //大分类,总裁 校园 科幻
    private String category;
    //作者
    private String author;
    //作品简介
    @Column(columnDefinition = "text")
    private String summary;
    //封面图(列表)
    private String coverPic;
    //详情页图片
    private String detailPic;
    //排序权值
    private Integer sort = 1;
    //状态:1连载 2完结
    private Integer status = 1;

}
