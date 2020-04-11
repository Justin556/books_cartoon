package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
//小说点赞
@Table(name = "t_book_likes")
public class BookLikes extends BaseEntity {
    //用户id
    private Integer userId;
    //小说id
    private Integer bid;
}
