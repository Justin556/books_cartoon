package com.app.books.service.serviceImpl;

import com.app.books.dto.BookQuery;
import com.app.books.entity.BookInfo;
import com.app.books.entity.Comic;
import com.app.books.entity.UserSendLog;
import com.app.books.mapper.BookMapper;
import com.app.books.result.Result;
import com.app.books.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Result bookList(BookQuery bookQuery) {
        PageHelper.startPage(bookQuery.getPageNumber(),bookQuery.getPageSize());//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<Comic> list = bookMapper.findAll(bookQuery);
        PageInfo<Comic> pageInfo = new PageInfo<Comic>(list);
        return Result.success(pageInfo);
    }

    @Override
    public Result details(int bookId) {
        BookInfo bookInfo = bookMapper.details(bookId);
        return Result.success(bookInfo);
    }

    @Override
    public void userSend(UserSendLog userSendLog) {
        bookMapper.userSend(userSendLog);
    }

    @Override
    public Result userSendList(Integer bookId) {

        return null;
    }
}
