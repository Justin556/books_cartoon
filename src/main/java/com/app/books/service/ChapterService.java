package com.app.books.service;

import com.app.books.vo.ChapterQuery;

public interface ChapterService {

    Integer addChapter(ChapterQuery chapterQuery);

    ChapterQuery selectChapter(String id,int type);
}
