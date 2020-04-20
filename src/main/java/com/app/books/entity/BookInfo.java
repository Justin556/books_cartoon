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
    private Integer episodes;
    //是否精选推荐 1是 0否
    private Integer isRecommend;
    //阅读数量
    private Integer readNum;
    //从第几章开始付费
    private Integer chargeNum;
    //每章多少钱
    private Integer chargeMoney;
    //人气值
    private Integer reader;
    //新书/非新书
    private Integer isNew;
    //小说id
    private Integer bid;

}
