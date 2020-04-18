package com.lee.video.entity.xs;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "t_comment")
//书或漫画评论
public class Comment extends BaseEntity {
    //书（漫画）id
    private Integer outId;
    //评论
    private String commentInfo;
    //作者
    private Integer userId;
    //书（漫画）章节id
    private Integer zjId;

}
