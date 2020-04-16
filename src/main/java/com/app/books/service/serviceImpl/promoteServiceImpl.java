package com.app.books.service.serviceImpl;

import com.app.books.dto.ComicQuery;
import com.app.books.dto.UserQuery;
import com.app.books.entity.Comic;
import com.app.books.entity.User;
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
    public Result promoteList(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNumber(),userQuery.getPageSize());//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<User> list = promoteMapper.findAllLowerLevel(userQuery.getId());
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return Result.success(pageInfo);
    }

    @Override
    public Result addPromote(UserRetailLevel userRetailLevel) {
        return Result.success(promoteMapper.addPromote(userRetailLevel));
    }


}
