package com.app.books.service.serviceImpl;

import com.app.books.config.AuthenticationInterceptor;
import com.app.books.mapper.ChapterMapper;
import com.app.books.service.ChapterService;
import com.app.books.vo.ChapterQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public Integer addChapter(ChapterQuery chapterQuery) {
        return chapterMapper.addChapter(chapterQuery);
    }

    @Override
    public ChapterQuery selectChapter(String id,int type) {
        if(authenticationInterceptor.userId=="null"&&authenticationInterceptor.userId.equals("null")){
            return null;
        }
        ChapterQuery chapterQuery=new ChapterQuery();
        chapterQuery.setOutId(Integer.parseInt(id));
        chapterQuery.setType(type);
        chapterQuery.setUserId(Integer.parseInt(authenticationInterceptor.userId));
        return chapterMapper.selectChapter(chapterQuery);
    }
}
