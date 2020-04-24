package com.app.books.service.serviceImpl;

import com.alibaba.druid.sql.visitor.functions.Lcase;
import com.app.books.entity.*;
import com.app.books.mapper.UserMapper;
import com.app.books.pojo.BookDetailsPojo;
import com.app.books.vo.BookQuery;
import com.app.books.mapper.BookMapper;
import com.app.books.result.Result;
import com.app.books.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result bookList(BookQuery bookQuery) {
        PageHelper.startPage(bookQuery.getPageNumber(),bookQuery.getPageSize());
        List<Comic> list = bookMapper.findAll(bookQuery);
        PageInfo<Comic> pageInfo = new PageInfo<Comic>(list);
        return Result.success(pageInfo);
    }

    @Override
    public BookDetailsPojo details(Integer bookId) {
        BookDetailsPojo bookDetailsPojo = bookMapper.details(bookId);
        if (bookDetailsPojo == null){
            return null;
        }
        bookDetailsPojo.setSendList(bookMapper.userSendList(bookId));
        bookDetailsPojo.setCommentList(bookMapper.commentList(bookId));
        bookDetailsPojo.setBookEpisodeList(bookMapper.bookEpisodeList(bookId));
        return bookDetailsPojo;
    }

    @Override
    public void userSend(User user, Integer bookId, Integer amount) {
        //新增打赏记录表
        bookMapper.userSend(new UserSendLog(new Date(), user.getId(), bookId, amount));
        //用户的书币减少
        userMapper.reduceBookCurrency(amount, user.getId());
        //新增书币变动表
        UserCurrencyLog userCurrencyLog = new UserCurrencyLog();
        userCurrencyLog.setCreateTime(new Date());
        userCurrencyLog.setUserId(user.getId());
        userCurrencyLog.setUserName(user.getUserName());
        userCurrencyLog.setCurrencyType(3);
        userCurrencyLog.setCurrency(amount);
        userMapper.insertUserCurrencyLog(userCurrencyLog);//添加书币记录
    }

    @Override
    public List<UserSendLog> userSendList(Integer bookId) {

        return bookMapper.userSendList(bookId);
    }

    @Override
    public void insertComment(User user, Integer bookId, String commentInfo) {
        bookMapper.insertComment(new Comment(new Date(), bookId, commentInfo, user.getId()));
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
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> map4 = new HashMap<>();
        map1.put("title", "猜你喜欢");
        map1.put("list", bookMapper.maybeLike());
        map2.put("title", "大家都在看");
        map2.put("list", bookMapper.watchTogether());
        map3.put("title", "女生喜欢");
        map3.put("list", bookMapper.girlLike());
        map4.put("title", "男生喜欢");
        map4.put("list", bookMapper.boyLike());
        lists.add(map1);
        lists.add(map2);
        lists.add(map3);
        lists.add(map4);
        return lists;
    }

    /**
     * 单个章节内容
     * @param jiNo
     * @return
     */
    @Override
    public String episodesContent(Integer jiNo) {
        return bookMapper.episodesContent(jiNo);
    }

    @Override
    public PageInfo<Book> homePageList(Integer pageNumber, Integer pageSize, Integer status) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Book> list;
        switch(status){
            case 1 ://猜你喜欢
                list = bookMapper.maybeLikeAll();
                break;
            case 2 ://大家都在看
                list = bookMapper.watchTogetherAll();
                break;
            case 3 ://女生喜欢
                list = bookMapper.girlLikeAll();
                break;
            case 4 ://男生喜欢
                list = bookMapper.boyLikeAll();
                break;
            default :
                list = null;
        }

        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        return pageInfo;
    }


    /*@Override
    public void bookLike() {

        bookMapper.insertBookLike(bookLikes);
    }*/
}
