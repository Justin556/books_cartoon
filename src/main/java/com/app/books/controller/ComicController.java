package com.app.books.controller;


import com.app.books.dto.ComicQuery;
import com.app.books.service.ComicService;
import com.app.books.utils.Result;
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
    public Result getDuo(ComicQuery comicQuery) {
        return comicService.comicList(comicQuery);
    }

}
