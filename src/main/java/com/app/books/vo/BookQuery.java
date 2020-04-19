package com.app.books.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 小说列表查询条件
 */
@Getter
@Setter
public class BookQuery {

    private String name;

    private String type;

    private int pageSize;

    private int pageNumber;

    private int status;
}
