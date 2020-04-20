package com.app.books.entity;;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class Book {
    private Integer id;

    private Date createTime;

    private Date updateTime;
    //书名
    private String title;
    //大分类：0：玄幻魔法； 1：武侠修真； 2：都市言情； 3：历史军事； 4：侦探推理； 5：网游动漫； 6：科幻灵异； 7：其他类型
    private Integer category;
    //作者
    private String author;
    //作品简介
    @Column(columnDefinition = "text")
    private String summary;
    //封面图(列表)
    private String coverPic;
    //详情页图片
    private String detailPic;
    //排序权值
    private Integer sort;
    //状态:1连载 2完结
    private Integer status;

}
