package com.app.books.controller;

import com.app.books.dto.BookQuery;
import com.app.books.dto.ComicQuery;
import com.app.books.result.Result;
import com.app.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book/")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("page")
    public Result page(BookQuery bookQuery) {
        return bookService.bookList(bookQuery);
    }

    @GetMapping("homePage")
    public Result homePage(BookQuery bookQuery) {
        return bookService.homePage(bookQuery);
    }

    /**
     * 单部小说详情
     * @param bookQuery
     * @return
     */
    @GetMapping("details")
    public Result details(int bookId) {
        return bookService.details(bookId);
    }

}

