package com.app.books.service;

import com.app.books.entity.*;
import com.app.books.pojo.BookDetailsPojo;
import com.app.books.vo.BookQuery;
import com.app.books.result.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BookService {
    Result bookList(BookQuery bookQuery);

    BookDetailsPojo details(Integer bookId);

    void userSend(User user, Integer bookId, Integer amount);

    List<UserSendLog> userSendList(Integer bookId);

    void insertComment(User user, Integer bookId, String commentInfo);

    List<Comment> commentList(Integer bookId);

    List<Map<String, Object>>  categoryList();

    PageInfo<Book> categoryPageList(Integer pageNumber, Integer pageSize, Integer category);

    List<Map<String, Object>> homePage();

    String episodesContent(Integer jiNo);

    PageInfo<Book> homePageList(Integer pageNumber, Integer pageSize, Integer status);

    /*void bookLike();*/
}
