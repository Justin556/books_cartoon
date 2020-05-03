package com.app.books.mapper;

import com.app.books.entity.Article;
import com.app.books.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {
    @Select("SELECT author, \n" +
            "create_time as createTime, \n" +
            "body, \n" +
            "title \n" +
            "FROM t_article ORDER BY createTime DESC")
    List<Article> getArticleList();
}
