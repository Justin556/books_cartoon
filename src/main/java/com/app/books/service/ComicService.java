package com.app.books.service;

import com.app.books.vo.ComicQuery;
import com.app.books.result.Result;

public interface ComicService {
    Result comicList(ComicQuery comicQuery);

    Result details(ComicQuery comicQuery);

    Result homePage(ComicQuery comicQuery);

}
