package com.app.books.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.math.BigDecimal;

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

    private int status;
}
