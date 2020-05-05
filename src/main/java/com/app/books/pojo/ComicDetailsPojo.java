package com.app.books.pojo;

import com.app.books.entity.BookEpisodes;
import com.app.books.entity.ComicEpisodes;
import com.app.books.entity.Comment;
import com.app.books.entity.UserSendLog;
import com.app.books.vo.ChapterQuery;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ComicDetailsPojo {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //漫画标题
    private String title;
    //分类逗号拼接 1总裁2穿越3校园
    private String category;
    //作者
    private String author;
    //作品简介
    @Lob
    @Column(columnDefinition = "text")
    private String summary;
    //封面图(列表)
    private String coverPic;
    //详情页图片
    private String detailPic;
    //排序权值
    private Integer sort = 1;
    //状态:1连载 2完结
    private String status;

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
    //是否收藏 0未收藏 1收藏
    private Integer collectStatus;

    //是否点赞 0未点赞 1点赞
    private Integer likeStatus;
    //打赏列表
    private List<UserSendLog> sendList;
    //评论列表
    private List<Comment> commentList;
    //章节列表
    private List<ComicEpisodes> comicEpisodes;



    private ChapterQuery chapterQuery;

}
