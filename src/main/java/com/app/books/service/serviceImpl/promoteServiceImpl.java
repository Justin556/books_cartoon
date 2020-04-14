package com.app.books.service.serviceImpl;

import com.app.books.dto.ComicQuery;
import com.app.books.entity.Comic;
import com.app.books.entity.UserRetailLevel;
import com.app.books.mapper.ComicMapper;
import com.app.books.mapper.PromoteMapper;
import com.app.books.result.Result;
import com.app.books.service.ComicService;
import com.app.books.service.PromoteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class promoteServiceImpl implements PromoteService {

    @Autowired
    private PromoteMapper promoteMapper;


    @Override
    public Result promoteList() {
        return Result.success(promoteMapper.findAllLowerLevel("123"));
    }

    @Override
    public Result addPromote(UserRetailLevel userRetailLevel) {
        return Result.success(promoteMapper.addPromote(userRetailLevel));
    }


}
