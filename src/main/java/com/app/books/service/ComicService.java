package com.app.books.service;

import com.app.books.entity.ComicLikes;
import com.app.books.vo.ComicQuery;
import com.app.books.result.Result;

public interface ComicService {
    Result comicList(ComicQuery comicQuery);

    Result details(String comicId);

    Result homePage(ComicQuery comicQuery);

    Result bannerDetails(String comicId);

    Result addComicLikes(ComicLikes comicLikes);

    Result sendPage(ComicQuery comicQuery);

    Result commentPage(ComicQuery comicQuery);
}
