package com.app.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Date createTime;

    private Date updateTime;

}
