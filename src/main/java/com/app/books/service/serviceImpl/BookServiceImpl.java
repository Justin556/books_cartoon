package com.app.books.service.serviceImpl;

import com.app.books.entity.*;
import com.app.books.exception.CustomerException;
import com.app.books.mapper.ChapterMapper;
import com.app.books.mapper.UserMapper;
import com.app.books.pojo.BookDetailsPojo;
import com.app.books.result.Result;
import com.app.books.utils.RedisUtil;
import com.app.books.vo.BookParams;
import com.app.books.mapper.BookMapper;
import com.app.books.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public PageInfo<Book> bookList(BookParams bookParams) {
        PageHelper.startPage(bookParams.getPageNumber(), bookParams.getPageSize());
        List<Book> list = bookMapper.findAll(bookParams);
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        return pageInfo;
    }

    @Override
    public BookDetailsPojo details(HttpServletRequest request, Integer bookId) {
        System.out.println("************1***********" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        BookDetailsPojo bookDetailsPojo = bookMapper.details(bookId);
        if (bookDetailsPojo == null){
            return null;
        }
        System.out.println("************2***********" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        bookDetailsPojo.setSendList(bookMapper.userSendList(bookId));
        System.out.println("************3***********" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        bookDetailsPojo.setCommentList(bookMapper.commentList(bookId));
        System.out.println("************4***********" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        if (redisUtil.hasKey(bookId + ":episodeList") && redisUtil.get(bookId + ":episodeList") != null) {
            bookDetailsPojo.setBookEpisodeList((List<BookEpisodes>)redisUtil.get(bookId + ":episodeList"));
        }else {
            bookDetailsPojo.setBookEpisodeList(bookMapper.bookEpisodeList(bookId));
        }

        System.out.println("************5***********" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        String token = request.getHeader("token");
        if (token != null) {//已登录
            Integer userId = (Integer) redisUtil.get(token);
            if (bookMapper.getLikeIdByBookIdAndUserId(bookId, userId) == null) {//未点赞
                bookDetailsPojo.setIsLiked(0);
            }else {//已点赞
                bookDetailsPojo.setIsLiked(1);
            }

            if (bookMapper.getCollectIdByBookIdAndUserId(bookId, userId) == null) {//未收藏
                bookDetailsPojo.setIsCollected(0);
            }else {//已收藏
                bookDetailsPojo.setIsCollected(1);
            }
            //获取该小说最后观看章节id
            Integer newestChapterId = chapterMapper.getNewestChapter(bookId, 1);
            bookDetailsPojo.setNewestChapterId(newestChapterId);
        }
        System.out.println("************6***********" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return bookDetailsPojo;
    }

    @Override
    public void userSend(User user, Integer bookId, Integer amount,Integer type) {
        //新增打赏记录表
        bookMapper.userSend(new UserSendLog(new Date(), user.getId(), bookId, amount,type));
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
    public PageInfo<UserSendLog> userSendList(Integer pageNumber, Integer pageSize, Integer bookId) {
        PageHelper.startPage(pageNumber,pageSize);
        List<UserSendLog> list = bookMapper.userSendList(bookId);
        PageInfo<UserSendLog> pageInfo = new PageInfo<UserSendLog>(list);
        return pageInfo;
    }

    @Override
    public void insertComment(User user, Integer bookId, String commentInfo) {
        bookMapper.insertComment(new Comment(new Date(), bookId, commentInfo, user.getId()));
    }

    @Override
    public PageInfo<Comment> commentList(Integer pageNumber, Integer pageSize, Integer bookId) {
        PageHelper.startPage(pageNumber,pageSize);
        List<Comment> list = bookMapper.commentList(bookId);
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
        return pageInfo;
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

    @Override
    public void bookLike(Integer bookId, Integer userId) {
        if (bookMapper.getLikeIdByBookIdAndUserId(bookId, userId) == null) {//未点赞
            bookMapper.insertBookLike(new BookLikes(new Date(), userId, bookId));
        }else {
            //如果已点赞则取消点赞
            bookMapper.deleteBookLike(bookId, userId);
        }
    }

    @Override
    public void bookCollect(Integer bookId, Integer userId) {
        if (bookMapper.getCollectIdByBookIdAndUserId(bookId, userId) == null) {//未收藏
            Book book = bookMapper.getBookById(bookId);
            bookMapper.insertBookCollect(new BookCollect(new Date(), userId, bookId, book.getTitle(), book.getCoverPic(), book.getSummary()));
        }else {
            //如果已收藏则取消收藏
            bookMapper.deleteBookCollect(bookId, userId);
        }
    }

    @Override
    public List<BookCollect> bookCollectList(Integer userId) {
        return bookMapper.getBookCollectList(userId);
    }

    @Override
    public Result ranking(BookParams bookParams) {
        PageHelper.startPage(bookParams.getPageNumber(),bookParams.getPageSize());//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<Book> list = bookMapper.ranking();
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        return Result.success(pageInfo);
    }
}
