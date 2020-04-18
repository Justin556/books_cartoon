package com.lee.video.entity.xs;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
//漫画列表
@Table(name = "t_comic")
public class ComicInfo extends BaseEntity {
    //打赏书币
    private Integer send = 0;
    //属性:1免费 2付费
    private Integer freeType = 2;
    //最新多少集(话)
    private Integer episodes = 0;
    //人气值
    private Integer reader = 0;
    //点赞数
    private Integer likes = 0;
    //收藏数
    private Integer collect = 0;
    //新书/非新书
    private Integer isNew = 0;
    //是否精选推荐 1是 0否
    private Integer isRecommend = 0;
    //阅读数量
    private Integer readNum = 0;
    //付费章节
    private Integer chargeNum = 0;
    //付费章节金额
    private Integer chargeMoney = 0;
    //漫画id
    private Integer comicId;

}
