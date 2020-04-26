package com.app.books.service;

import com.app.books.entity.*;
import com.app.books.pojo.BookDetailsPojo;
import com.app.books.vo.BookParams;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BookService {
    PageInfo<Book> bookList(BookParams bookParams);

    BookDetailsPojo details(HttpServletRequest request, Integer bookId);

    void userSend(User user, Integer bookId, Integer amount,Integer type);

    PageInfo<UserSendLog> userSendList(Integer pageNumber, Integer pageSize, Integer bookId);

    void insertComment(User user, Integer bookId, String commentInfo);

    PageInfo<Comment> commentList(Integer pageNumber, Integer pageSize, Integer bookId);

    List<Map<String, Object>>  categoryList();

    PageInfo<Book> categoryPageList(Integer pageNumber, Integer pageSize, Integer category);

    List<Map<String, Object>> homePage();

    String episodesContent(Integer jiNo);

    PageInfo<Book> homePageList(Integer pageNumber, Integer pageSize, Integer status);

    void bookLike(Integer bookId, Integer userId);

    void bookCollect(Integer bookId, Integer userId);
}
