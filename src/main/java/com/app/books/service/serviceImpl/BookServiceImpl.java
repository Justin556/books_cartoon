package com.app.books.service.serviceImpl;

import com.app.books.pojo.BookDetailsPojo;
import com.app.books.vo.BookQuery;
import com.app.books.entity.BookInfo;
import com.app.books.entity.Comic;
import com.app.books.entity.Comment;
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
    public BookDetailsPojo details(Integer bookId) {
        BookDetailsPojo bookDetailsPojo = bookMapper.details(bookId);
        bookDetailsPojo.setSendList(bookMapper.userSendList(bookId));
        bookDetailsPojo.setCommentList(bookMapper.commentList(bookId));
        bookDetailsPojo.setBookEpisodeList(bookMapper.bookEpisodeList(bookId));
        return bookDetailsPojo;
    }

    @Override
    public void userSend(UserSendLog userSendLog) {
        bookMapper.userSend(userSendLog);
    }

    @Override
    public Result userSendList(Integer bookId) {

        return Result.success(bookMapper.userSendList(bookId));
    }

    @Override
    public void insertComment(Comment comment) {
        bookMapper.insertComment(comment);
    }

    @Override
    public Result commentList(Integer bookId) {
        return Result.success(bookMapper.commentList(bookId));
    }
}
