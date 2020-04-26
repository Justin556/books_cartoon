package com.app.books.service;

import com.app.books.entity.ComicCollect;
import com.app.books.entity.ComicLikes;
import com.app.books.entity.UserSendLog;
import com.app.books.vo.ComicQuery;
import com.app.books.result.Result;

public interface ComicService {
    Result comicList(ComicQuery comicQuery);

    Result details(String comicId);

    Result homePage(ComicQuery comicQuery);

    Result bannerDetails(String chapterId);

    Result addComicLikes(ComicLikes comicLikes);

    Result sendPage(ComicQuery comicQuery);

    Result commentPage(ComicQuery comicQuery);

    Result closedComic(ComicCollect comicCollect);

    Result userSend(UserSendLog userSendLog);

    Result readingHistory(ComicQuery comicQuery);

    Result continueSee(ComicQuery comicQuery);

    Result closedHistory(ComicCollect comicCollect);

    Result historicalRecord( );
}
