package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.Date;

;

@Getter
@Setter
//漫画类型
public class ComicType {
    private Integer id;

    private String type;

}
