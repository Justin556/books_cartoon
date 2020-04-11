package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
//漫画轮播图
@Table(name = "t_comic_banner")
public class ComicBanner extends BaseEntity {
    //图片路径
    private String pic;
    //漫画ID
    private Integer comicId;
}
