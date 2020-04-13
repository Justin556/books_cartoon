package com.app.books.service.serviceImpl;

import com.app.books.dto.ComicParams;
import com.app.books.dto.ComicQuery;
import com.app.books.entity.Comic;
import com.app.books.mapper.ComicMapper;
import com.app.books.service.ComicService;
import com.app.books.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicMapper comicMapper;

    @Override
    public Result comicList(ComicQuery comicQuery) {
        PageHelper.startPage(comicQuery.getPageNumber(),comicQuery.getPageSize());//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<Comic> list = comicMapper.findAll();
        PageInfo<Comic> pageInfo = new PageInfo<Comic>(list);
        return Result.success(pageInfo, (int) pageInfo.getTotal());
    }
}