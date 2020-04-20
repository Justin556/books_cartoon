package com.app.books.controller;

import com.app.books.vo.BookQuery;
import com.app.books.entity.Comment;
import com.app.books.entity.UserSendLog;
import com.app.books.result.Result;
import com.app.books.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
     * 包含：打赏总数，打赏列表，点赞总数，收藏总数，评论总数，评论列表，章节总数，所有章节的title
     * @param bookId
     * @return
     */
    @GetMapping("details")
    @ApiOperation(value = "单部小说详情")
    public Result details(Integer bookId) {
        return Result.success(bookService.details(bookId));
    }

    /**
     * 用户打赏
     * @param userSendLog
     * @return
     */
    @PutMapping("userSend")
    @ApiOperation(value = "新增用户打赏")
    public Result userSend(UserSendLog userSendLog) {
        bookService.userSend(userSendLog);
        return Result.success();
    }

    /**
     * 小说打赏列表
     * @param bookId
     */
    @GetMapping("userSendList")
    @ApiOperation(value = "打赏列表")
    public Result userSendList(Integer bookId) {
        return Result.success(bookService.userSendList(bookId));
    }

    @PutMapping("comment")
    @ApiOperation(value = "新增用户评论")
    public Result comment(Comment comment) {
        bookService.insertComment(comment);
        return Result.success();
    }

    @GetMapping("commentList")
    @ApiOperation(value = "评论列表")
    public Result commentList(Integer bookId) {
        return Result.success(bookService.commentList(bookId));
    }

    @GetMapping("categoryList")
    @ApiOperation(value = "大分类列表")
    public Result categoryList() {
        return Result.success(bookService.categoryList());
    }

    @GetMapping("categoryPageList")
    @ApiOperation(value = "分类小说分页列表")
    public Result categoryPageList() {
        return Result.success(bookService.categoryList());
    }
}

