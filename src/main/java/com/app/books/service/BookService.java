package com.app.books.service;

import com.app.books.pojo.BookDetailsPojo;
import com.app.books.vo.BookQuery;
import com.app.books.entity.Comment;
import com.app.books.entity.UserSendLog;
import com.app.books.result.Result;

public interface BookService {
    Result bookList(BookQuery bookQuery);

    BookDetailsPojo details(Integer bookId);

    void userSend(UserSendLog userSendLog);

    Result userSendList(Integer bookId);

    void insertComment(Comment comment);

    Result commentList(Integer bookId);
}
