package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
//小说轮播图
@Table(name = "t_book_banner")
public class BookBanner extends BaseEntity {
    //图片路径
    private String pic;
    //小说ID
    private Integer bid;
}
