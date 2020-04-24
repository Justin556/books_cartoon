package com.app.books.service;

import com.app.books.vo.ChapterQuery;
import org.apache.ibatis.annotations.Select;

public interface ChapterService {

    Integer addChapter(ChapterQuery chapterQuery);

    ChapterQuery selectChapter(String id,int type);
}
