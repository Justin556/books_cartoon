package com.app.books.entity;

import lombok.Data;

/**
 * 用来存放某个章节的内容
 */
@Data
public class BookEpisodesContent {
    private Integer id;

    //内容
    private String content;
}
