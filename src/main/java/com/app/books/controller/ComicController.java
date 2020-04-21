package com.app.books.controller;


import com.app.books.entity.ComicLikes;
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
     * @param comicId
     */
    @GetMapping("details")
    @ApiOperation(value = "漫画详情")
    public Result details(String comicId) {
        return comicService.details(comicId);
    }

    /**
     * 漫画更多打赏
     * @param comicQuery
     */
    @GetMapping("sendPage")
    @ApiOperation(value = "漫画更多打赏")
    public Result sendPage(ComicQuery comicQuery) {
        return comicService.sendPage(comicQuery);
    }

    /**
     * 漫画更多评论
     * @param comicQuery
     */
    @GetMapping("commentPage")
    @ApiOperation(value = "漫画更多评论")
    public Result commentPage(ComicQuery comicQuery) {
        return comicService.commentPage(comicQuery);
    }

    /**
     * 漫画某一集内容
     * @param comicId
     */
    @GetMapping("getComicPicture")
    @ApiOperation(value = "漫画某一集内容")
    public Result getComicPicture(String comicId) {
        return comicService.bannerDetails(comicId);
    }

    /**
     * 漫画点赞
     * @param comicLikes
     */
    @GetMapping("likeComic")
    @ApiOperation(value = "漫画点赞")
    public Result likeComic(ComicLikes comicLikes) {
        return comicService.addComicLikes(comicLikes);
    }

    /**
     * 漫画打赏
     * @param comicQuery
     */
    @GetMapping("exceptionalComic")
    @ApiOperation(value = "漫画打赏")
    public Result exceptionalComic(ComicQuery comicQuery) {
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
