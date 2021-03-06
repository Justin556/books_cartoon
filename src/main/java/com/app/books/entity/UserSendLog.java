package com.app.books.entity;;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户打赏
 */
@Data
public class UserSendLog {
    private Integer id;

    private String createTime;

    private Date updateTime;

    //用户id
    private Integer userId;

    //打赏书币（金额）类型（0书币，1金额）
    private Integer sendType;

    //1小说2漫画
    private Integer type;

    //打赏小说或漫画id
    private Integer outId;

    //打赏金额
    private Integer amount;


    //打赏小说或漫画的某个章节id
    private Integer zjId;

    //----------------子查询字段--------------------
    //用户名
    private String userName;

    //打赏总金额
    private Integer userAmount;

    //头像
    private String portrait;

    public UserSendLog() {
    }

    public UserSendLog(Date createTime, Integer userId, Integer outId, Integer amount,Integer type) {
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        this.createTime = sdf.format(createTime);
        this.userId = userId;
        this.outId = outId;
        this.amount = amount;
        this.type = type;
    }

    public void setCreateTime(Date createTime){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTime=sdf.format(createTime);
    }
}
