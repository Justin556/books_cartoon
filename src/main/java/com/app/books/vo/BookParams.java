package com.app.books.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 小说列表查询条件
 */
@Getter
@Setter
public class BookParams {

    private String name;

    private Integer category;

    private Integer status;

    private int pageSize;

    private int pageNumber;
}
