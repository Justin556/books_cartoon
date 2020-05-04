package com.app.books.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 漫画列表查询条件
 */
@Getter
@Setter
public class ComicQuery {

    private String name;

    private String type;

    private int pageSize;

    private int pageNumber;

    private String status;

    private String category;

    private String comicId;

    private Integer like;

    private Integer send;
    private Integer comments;
    private Integer collect;
}
