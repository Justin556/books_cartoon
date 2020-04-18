package com.lee.video.entity.xs;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_article")
//客服消息
public class Article extends BaseEntity {
    //标题
    private String title;
    //作者
    private String author;
    //排序
    private Integer sort;
    //封面图片
    private String cover;
    //封面图是否显示在正文
    private Integer showCover;
    //正文内容
    @Lob
    @Column(columnDefinition="text")
    private String body;
}
