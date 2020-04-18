package com.lee.video.entity.xs;;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
//漫画收藏
@Table(name = "t_comic_collect")
public class ComicCollect extends BaseEntity {
    //漫画ID
    private Integer comicId;
    //当前阅读章节
    private Integer readNumber;
    //用户ID
    private Integer userId;
    //漫画标题
    private String title;
    //封面图(列表)
    private String coverPic;
}
