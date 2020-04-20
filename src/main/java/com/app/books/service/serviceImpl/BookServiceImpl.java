package com.app.books.service.serviceImpl;

import com.app.books.entity.*;
import com.app.books.pojo.BookDetailsPojo;
import com.app.books.vo.BookQuery;
import com.app.books.mapper.BookMapper;
import com.app.books.result.Result;
import com.app.books.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<UserSendLog> userSendList(Integer bookId) {

        return bookMapper.userSendList(bookId);
    }

    @Override
    public void insertComment(Comment comment) {
        bookMapper.insertComment(comment);
    }

    @Override
    public List<Comment> commentList(Integer bookId) {
        return bookMapper.commentList(bookId);
    }

    @Override
    public List<Map<String, Object>> categoryList() {
        List<Map<String, Object>> lists = new ArrayList<>();

        for (int i = 0; i <=6; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("category", i);
            map.put("list", bookMapper.getBookByCategory(i));
            lists.add(map);
        }
        return lists;
    }

    @Override
    public PageInfo<Book> categoryPageList(Integer pageNumber, Integer pageSize, Integer category) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Book> list = bookMapper.categoryPageList(category);
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        return pageInfo;
    }

    public List<Map<String, Object>> homePage() {
        List<Map<String, Object>> lists = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("title", "猜你喜欢");
        map1.put("list", bookMapper.maybeLike());
        map2.put("title", "大家都在看");
        map2.put("list", bookMapper.watchTogether());
        lists.add(map1);
        lists.add(map2);
        return lists;
    }
}
