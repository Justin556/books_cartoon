package com.lee.video.entity.xs;;


import com.lee.video.entity.xs.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 每有一个注册用户，这张表就找到它对应的分销级别的用户插入一条数据，来提供给后续查询更方便
 */
@Data
@Entity
@Table(name = "t_user_retail_level")
public class UserRetailLevel extends BaseEntity {

    //用户编号
    private Integer userId;

    //上一级
    private String upOne;

    //上二级
    private String upTwo;

    //上三级
    private String upThree;

    //下一级
    private String downOne;

    //下二级
    private String downTwo;

    //下三级
    private String downThree;
}
