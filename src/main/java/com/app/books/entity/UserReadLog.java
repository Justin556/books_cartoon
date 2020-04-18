package com.lee.video.entity.xs;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
//阅读记录
@Table(name = "t_read_log")
public class UserReadLog extends BaseEntity {
    //用户id
    private Integer userId;

    //小说（漫画）id
    private Integer outId;

    //小说（漫画）章节id
    private Integer zjId;
}
