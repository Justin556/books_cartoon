package com.app.books.vo;

import lombok.Data;

@Data
public class BookRankingParams {
    private int pageNumber;

    private int pageSize;

    //排行类型（1：点赞  2：打赏  3：收藏  4：评论）
    private Integer type;
}
