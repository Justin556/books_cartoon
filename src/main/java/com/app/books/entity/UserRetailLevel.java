package com.app.books.entity;

import lombok.Data;

import java.util.Date;


/**
 * 每有一个注册用户，这张表就找到它对应的分销级别的用户插入一条数据，来提供给后续查询更方便
 */
@Data
public class UserRetailLevel {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    //上一级
    private Integer parentId;

    //用户编号
    private Integer userId;

    public UserRetailLevel(Date createTime, Integer parentId, Integer userId) {
        this.createTime = createTime;
        this.parentId = parentId;
        this.userId = userId;
    }
}
