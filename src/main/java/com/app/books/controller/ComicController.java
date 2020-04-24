package com.app.books.controller;


import com.app.books.entity.ComicCollect;
import com.app.books.entity.ComicLikes;
import com.app.books.entity.UserSendLog;
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
     * @param chapterId
     */
    @GetMapping("getComicPicture")
    @ApiOperation(value = "漫画某一集内容")
    public Result getComicPicture(String chapterId) {
        return comicService.bannerDetails(chapterId);
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
     * @param userSendLog
     */
    @GetMapping("exceptionalComic")
    @ApiOperation(value = "漫画打赏")
    public Result userSend(UserSendLog userSendLog) {
        return comicService.userSend(userSendLog);
    }

    /**
     * 漫画收藏
     * @param comicCollect
     */
    @GetMapping("closedComic")
    @ApiOperation(value = "漫画收藏")
    public Result closedComic(ComicCollect comicCollect) {
        return comicService.closedComic(comicCollect);
    }
    /**
     * 收藏历史
     * @param comicCollect
     */
    @GetMapping("closedHistory")
    @ApiOperation(value = "收藏历史")
    public Result closedHistory(ComicCollect comicCollect) {
        return comicService.closedHistory(comicCollect);
    }
    /**
     * 漫画阅读历史
     * @param comicQuery
     */
    @GetMapping("readingHistory")
    @ApiOperation(value = "漫画阅读历史")
    public Result readingHistory(ComicQuery comicQuery) {
        return comicService.readingHistory(comicQuery);
    }

    /**
     * 续看
     * @param comicQuery
     */
    @GetMapping("continueSee")
    @ApiOperation(value = "续看")
    public Result continueSee(ComicQuery comicQuery) {
        return comicService.continueSee(comicQuery);
    }


}
