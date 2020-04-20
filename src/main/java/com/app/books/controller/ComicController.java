package com.app.books.controller;


import com.app.books.vo.ComicQuery;
import com.app.books.result.Result;
import com.app.books.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    public Result page(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }
    /**
     * 漫画首页
     * @param comicQuery
     */
    @GetMapping("homePage")
    public Result homePage(ComicQuery comicQuery) {
        return comicService.homePage(comicQuery);
    }
    /**
     * 漫画详情
     * @param comicQuery
     */
    @GetMapping("details")
    public Result details(ComicQuery comicQuery) {
        return comicService.details(comicQuery);
    }

    /**
     * 漫画打赏列表
     * @param comicQuery
     */
    @GetMapping("exceptional")
    public Result exceptional(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

    /**
     * 漫画打赏列表
     * @param comicQuery
     */
    @GetMapping("exceptionalList")
    public Result exceptionalList(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

    /**
     * 漫画评论列表
     * @param comicQuery
     */
    @GetMapping("commentsList")
    public Result commentsList(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

    /**
     * 漫画某一集内容
     * @param comicQuery
     */
    @GetMapping("getComicPicture")
    public Result getComicPicture(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

    /**
     * 漫画点赞
     * @param comicQuery
     */
    @GetMapping("likeComic")
    public Result likeComic(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

    /**
     * 漫画收藏
     * @param comicQuery
     */
    @GetMapping("closedComic")
    public Result closedComic(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }
}
