package com.app.books.service;

import com.app.books.dto.ComicParams;
import com.app.books.dto.ComicQuery;
import com.app.books.utils.Result;
import org.springframework.data.domain.Page;

public interface ComicService {
    Result comicList(ComicQuery comicQuery);
}
