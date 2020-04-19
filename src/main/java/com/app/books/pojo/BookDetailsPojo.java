package com.app.books.pojo;

import com.app.books.entity.BookEpisodes;
import com.app.books.entity.Comment;
import com.app.books.entity.Exceptional;
import com.app.books.entity.UserSendLog;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class BookDetailsPojo {
    private Integer id;

    private Date createTime;
    //书名
    private String title;
    //大分类,总裁 校园 科幻
    private String category;
    //作者
    private String author;
    //作品简介
    private String summary;
    //封面图(列表)
    private String coverPic;
    //详情页图片
    private String detailPic;
    //排序权值
    private Integer sort;
    //状态:1连载 2完结
    private Integer status;
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

    //---------------------子查询字段-----------------------
    //打赏总数
    private Integer sendSum;
    //点赞总数
    private Integer likeSum;
    //收藏总数
    private Integer collectSum;
    //评论总数
    private Integer commentSum;
    //章节总数
    private Integer chapterSum;

    //打赏列表
    private List<UserSendLog> sendList;
    //评论列表
    private List<Comment> commentList;
    //章节列表
    private List<BookEpisodes> bookEpisodeList;
}
