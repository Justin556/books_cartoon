package com.app.books.controller;


import com.app.books.dto.ComicQuery;
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

    @GetMapping("page")
    public Result page(ComicQuery comicQuery) {
        return Result.success(comicService.comicList(comicQuery));
    }

    @GetMapping("details")
    public Result details(ComicQuery comicQuery) {
        return Result.success(comicService.comicList(comicQuery));
    }

    //打赏
    @GetMapping("exceptional")
    public Result exceptional(ComicQuery comicQuery) {
        return Result.success(comicService.comicList(comicQuery));
    }
}
