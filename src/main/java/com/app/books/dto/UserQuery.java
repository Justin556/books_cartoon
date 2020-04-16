package com.app.books.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 漫画列表查询条件
 */
@Getter
@Setter
public class UserQuery {

    private String name;

    private String id;

    private int pageSize;

    private int pageNumber;

}
