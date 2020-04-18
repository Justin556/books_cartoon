package com.lee.video.entity.xs;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_notice")
//公告
public class Notice extends BaseEntity {
    //标题
    private String title;
    //内容
    @Lob
    @Column(columnDefinition="text")
    private String content;
}
