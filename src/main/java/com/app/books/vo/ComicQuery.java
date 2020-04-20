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
}
