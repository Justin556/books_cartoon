package com.app.books.service;

import com.app.books.entity.Book;
import com.app.books.entity.Comic;
import com.app.books.pojo.BookDetailsPojo;
import com.app.books.vo.BookQuery;
import com.app.books.entity.Comment;
import com.app.books.entity.UserSendLog;
import com.app.books.result.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BookService {
    Result bookList(BookQuery bookQuery);

    BookDetailsPojo details(Integer bookId);

    void userSend(UserSendLog userSendLog);

    List<UserSendLog> userSendList(Integer bookId);

    void insertComment(Comment comment);

    List<Comment> commentList(Integer bookId);

    List<Map<String, Object>>  categoryList();

    PageInfo<Book> categoryPageList(Integer pageNumber, Integer pageSize, Integer category);

    List<Map<String, Object>> homePage();
}
