package com.lee.video.entity.xs;;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
//漫画分集内容
@Table(name = "t_comic_episodes")
public class ComicEpisodes extends BaseEntity {
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
