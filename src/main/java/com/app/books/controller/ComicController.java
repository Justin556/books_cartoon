package com.app.books.controller;


import com.app.books.vo.ComicQuery;
import com.app.books.result.Result;
import com.app.books.service.ComicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "漫画-业务接口")
@RequestMapping("/comic/")
@Controller
public class ComicController {

    @Autowired
    private ComicService comicService;
    /**
     * 漫画列表
     * @param comicQuery
     */
    @GetMapping("page")
    @ApiOperation(value = "分页数据")
    public Result page(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }
    /**
     * 漫画首页
     * @param comicQuery
     */
    @GetMapping("homePage")
    @ApiOperation(value = "首页数据")
    public Result homePage(ComicQuery comicQuery) {
        return comicService.homePage(comicQuery);
    }
    /**
     * 漫画详情
     * @param comicQuery
     */
    @GetMapping("details")
    @ApiOperation(value = "漫画详情")
    public Result details(ComicQuery comicQuery) {
        return comicService.details(comicQuery);
    }

    /**
     * 漫画打赏列表
     * @param comicQuery
     */
    @GetMapping("exceptional")
    @ApiOperation(value = "漫画打赏列表")
    public Result exceptional(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

    /**
     * 漫画打赏列表
     * @param comicQuery
     */
    @GetMapping("exceptionalList")
    @ApiOperation(value = "漫画打赏列表")
    public Result exceptionalList(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

    /**
     * 漫画评论列表
     * @param comicQuery
     */
    @GetMapping("commentsList")
    @ApiOperation(value = "漫画评论列表")
    public Result commentsList(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

    /**
     * 漫画某一集内容
     * @param comicQuery
     */
    @GetMapping("getComicPicture")
    @ApiOperation(value = "漫画某一集内容")
    public Result getComicPicture(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

    /**
     * 漫画点赞
     * @param comicQuery
     */
    @GetMapping("likeComic")
    @ApiOperation(value = "漫画点赞")
    public Result likeComic(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

    /**
     * 漫画收藏
     * @param comicQuery
     */
    @GetMapping("closedComic")
    @ApiOperation(value = "漫画收藏")
    public Result closedComic(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }
}
