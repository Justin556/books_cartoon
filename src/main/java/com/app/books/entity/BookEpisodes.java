package com.lee.video.entity.xs;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
//小说章节内容
@Table(name = "t_book_episodes")
public class BookEpisodes extends BaseEntity {
    //小说ID
    private Integer bid;
    //章节标题
    private String title;
    //章节编号
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
