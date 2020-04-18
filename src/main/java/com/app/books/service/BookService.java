package com.app.books.service;

import com.app.books.dto.BookQuery;
import com.app.books.dto.ComicQuery;
import com.app.books.result.Result;

public interface BookService {
    Result bookList(BookQuery bookQuery);

    Result details(int bookId);

    Result homePage(BookQuery bookQuery);
}
