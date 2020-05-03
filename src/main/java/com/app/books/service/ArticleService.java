package com.app.books.service;

import com.app.books.entity.Article;
import com.github.pagehelper.PageInfo;

public interface ArticleService {

    PageInfo<Article> getArticleList(Integer pageNumber, Integer pageSize);
}
