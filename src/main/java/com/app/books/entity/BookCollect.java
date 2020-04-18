package com.lee.video.entity.xs;;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
//小说收藏
@Table(name = "t_book_collect")
public class BookCollect extends BaseEntity {
    //小说ID
    private Integer bid;
    //收藏章节
    private Integer readNumber;
    //用户ID
    private Integer userId;
    //小说标题
    private String title;
    //封面图(列表)
    private String coverPic;
}
