package com.app.books.service.serviceImpl;

import com.app.books.vo.ComicQuery;
import com.app.books.entity.Comic;
import com.app.books.mapper.ComicMapper;
import com.app.books.result.Result;
import com.app.books.service.ComicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicMapper comicMapper;

    @Override
    public Result comicList(ComicQuery comicQuery) {
        PageHelper.startPage(comicQuery.getPageNumber(),comicQuery.getPageSize());//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<Comic> list = comicMapper.findAll(comicQuery);
        PageInfo<Comic> pageInfo = new PageInfo<Comic>(list);
        return Result.success(pageInfo);
    }

    @Override
    public Result details(ComicQuery comicQuery) {
        return Result.success(comicMapper.details(comicQuery));
    }

    @Override
    public Result homePage(ComicQuery comicQuery) {
        Map<String, List<Comic>> map =new HashMap<>();
        comicQuery.setCategory("");
        PageHelper.startPage(1,6);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<Comic> list=comicMapper.findAll(comicQuery);
        map.put("1",list);

        comicQuery.setCategory("后宫");
        PageHelper.startPage(1,6);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        list=comicMapper.findAll(comicQuery);
        map.put("2",list);

        comicQuery.setCategory("热血");
        PageHelper.startPage(1,6);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        list = comicMapper.findAll(comicQuery);
        map.put("3",list);

        comicQuery.setCategory("恋爱,古风");
        PageHelper.startPage(1,6);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        list=comicMapper.findAll(comicQuery);
        map.put("4",list);



        return Result.success(map);
    }
}
