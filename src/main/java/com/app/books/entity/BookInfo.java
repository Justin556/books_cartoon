package com.app.books.entity;;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
//小说基本信息
public class BookInfo {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //最新多少集(话)
    private Integer episodes = 0;
    //是否精选推荐 1是 0否
    private Integer isRecommend = 0;
    //阅读数量
    private Integer readNum = 0;
    //从第几章开始付费
    private Integer chargeNum = 0;
    //每章多少钱
    private Integer chargeMoney = 0;
    //人气值
    private Integer reader = 0;
    //新书/非新书
    private Integer isNew = 0;
    //小说id
    private Integer bid;

}
