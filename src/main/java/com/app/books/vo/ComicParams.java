package com.app.books.vo;

import javax.persistence.Column;
import javax.persistence.Lob;

/**
 * 漫画列表
 */

public class ComicParams {

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
    private Integer status = 1;
}
