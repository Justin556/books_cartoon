package com.app.books.controller;

import com.app.books.dto.BookQuery;
import com.app.books.dto.ComicQuery;
import com.app.books.entity.UserSendLog;
import com.app.books.result.Result;
import com.app.books.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "小说-业务接口")
@RequestMapping("/book/")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("page")
    @ApiOperation(value = "分页数据")
    public Result page(BookQuery bookQuery) {
        return bookService.bookList(bookQuery);
    }

    /*@GetMapping("homePage")
    public Result homePage(BookQuery bookQuery) {
        return bookService.homePage(bookQuery);
    }*/

    /**
     * 单部小说详情
     * @param bookId
     * @return
     */
    @GetMapping("details")
    @ApiOperation(value = "单部小说详情")
    public Result details(int bookId) {
        return bookService.details(bookId);
    }

    /**
     * 用户打赏
     * @param userSendLog
     * @return
     */
    @GetMapping("userSend")
    public Result userSend(UserSendLog userSendLog) {
        bookService.userSend(userSendLog);
        return Result.success();
    }

    /**
     * 小说打赏列表
     * @param bookId
     */
    @GetMapping("userSendList")
    @ApiOperation(value = "小说打赏列表")
    public Result userSendList(Integer bookId) {
        return bookService.userSendList(bookId);
    }
}

