package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
//漫画点赞
@Table(name = "t_comic_likes")
public class ComicLikes extends BaseEntity {
    //用户id
    private Integer userId;
    //漫画id
    private Integer comicId;
}
