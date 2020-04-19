package com.app.books.service;

import com.app.books.dto.BookQuery;
import com.app.books.dto.ComicQuery;
import com.app.books.entity.UserSendLog;
import com.app.books.result.Result;

public interface BookService {
    Result bookList(BookQuery bookQuery);

    Result details(int bookId);

    void userSend(UserSendLog userSendLog);

    Result userSendList(Integer bookId);
}
