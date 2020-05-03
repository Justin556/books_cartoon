package com.app.books.service.serviceImpl;

import com.app.books.entity.Article;
import com.app.books.entity.UserSendLog;
import com.app.books.mapper.ArticleMapper;
import com.app.books.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageInfo<Article> getArticleList(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Article> list = articleMapper.getArticleList();
        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        return pageInfo;
    }
}
