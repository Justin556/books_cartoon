package com.app.books.controller;

import com.app.books.result.Result;
import com.app.books.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "消息")
@RequestMapping("/article/")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("show")
    @ApiOperation(value = "获取最新消息")
    public Result show(Integer pageNumber, Integer pageSize) {
        return Result.success(articleService.getArticleList(pageNumber,pageSize));
    }
}
