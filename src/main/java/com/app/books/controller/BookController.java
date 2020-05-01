package com.app.books.controller;

import com.app.books.config.LoginRequired;
import com.app.books.entity.*;
import com.app.books.mapper.BookMapper;
import com.app.books.mapper.ChapterMapper;
import com.app.books.mapper.UserMapper;
import com.app.books.utils.RedisUtil;
import com.app.books.vo.BookParams;
import com.app.books.result.Result;
import com.app.books.service.BookService;
import com.app.books.vo.ChapterQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@Api(tags = "小说-业务接口")
@RequestMapping("/book/")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ChapterMapper chapterMapper;

    @GetMapping("page")
    @ApiOperation(value = "模糊/条件 搜索")
    public Result page(BookParams bookParams) {
        return Result.success(bookService.bookList(bookParams));
    }

    /**
     * 主页：猜你喜欢/大家一起看
     * @param
     * @return
     */
    @GetMapping("homePage")
    @ApiOperation(value = "主页：猜你喜欢/大家一起看/女生/男生")
    public Result homePage() {
        return Result.success(bookService.homePage());
    }

    @GetMapping("homePageList")
    @ApiOperation(value = "猜你喜欢/大家一起看/女生/男生-分页接口")
    public Result homePageList(Integer pageNumber, Integer pageSize, Integer status) {
        return Result.success(bookService.homePageList(pageNumber, pageSize, status));
    }

    /**
     * 单部小说详情
     * 包含：打赏总数，打赏列表，点赞总数，收藏总数，评论总数，评论列表，章节总数，所有章节的title
     * @param bookId
     * @return
     */
    @GetMapping("details")
    @ApiOperation(value = "单部小说详情")
    public Result details(HttpServletRequest request, Integer bookId) {
        return Result.success(bookService.details(request, bookId));
    }

    /**
     * 单个章节内容
     * @param chapterId
     * @return
     */
    @GetMapping("episodesContent")
    @ApiOperation(value = "单个章节内容")
    public Result episodesContent(HttpServletRequest request, Integer bid, Integer chapterId, Integer jiNo) {
        String token = request.getHeader("token");
        User user = userMapper.findUserById((Integer) redisUtil.get(token));
        if (token != null) {//如果已登录，向小说历史记录表插入或更新数据
            Integer userId = (Integer)redisUtil.get(request.getHeader("token"));
            ChapterQuery chapter = new ChapterQuery();
            chapter.setUserId(userId);
            chapter.setOutId(bid);
            chapter.setChapterId(chapterId);
            chapter.setType(1);
            chapter.setChapter(bookMapper.getEpisodesTitleById(chapterId));
            if (chapterMapper.selectChapter(chapter) != null) {
                chapterMapper.update(chapter);
            }else {
                chapterMapper.addChapter(chapter);
            }
        }

        Integer money = bookMapper.getMoneyByChapterId(chapterId).intValue();//阅读该章节需要的费用
        if (money != null || money != 0){//收费小说
            if (token == null){
                return Result.error(-1, "请登录！");
            }else if (!redisUtil.hasKey(token)) {
                return Result.error(-1, "token无效，请重新登录！");
            }

            if (user.getIsVip() == 0){//如果不是vip
                if (bookMapper.getIsPay(user.getId(), chapterId) == 0){//如果本章节没付过费
                    if (user.getBookCurrency() < money){//如果用户的书币不足以支付该章节费用
                        return Result.error(-1, "书币不足！");
                    }else {
                        userMapper.reduceBookCurrency(money, user.getId());//扣除用户书币
                        //新增书币变动表
                        UserCurrencyLog userCurrencyLog = new UserCurrencyLog();
                        userCurrencyLog.setCreateTime(new Date());
                        userCurrencyLog.setUserId(user.getId());
                        userCurrencyLog.setUserName(user.getUserName());
                        userCurrencyLog.setCurrencyType(5);
                        userCurrencyLog.setCurrency(money);
                        userMapper.insertUserCurrencyLog(userCurrencyLog);//添加书币记录

                        BookIsPay bookIsPay = new BookIsPay(user.getId(), chapterId, 1);
                        bookMapper.addBookIsPay();//标记该章节为已付费
                    }
                }
            }
        }
        return Result.success(bookService.episodesContent(jiNo));
    }

    /**
     * 打赏
     * @param request
     * @param bookId 小说id
     * @param amount 打赏书币金额
     * @return
     */
    @PostMapping("userSend")
    @ApiOperation(value = "打赏")
    @LoginRequired
    public Result userSend(HttpServletRequest request, Integer bookId, Integer amount) {
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        User user = userMapper.findUserById(userId);
        if (user.getBookCurrency() < amount) {
            return Result.error("书币不足");
        }
        bookService.userSend(user, bookId, amount,1);
        return Result.success();
    }

    @GetMapping("bookLike")
    @ApiOperation(value = "点赞/取消点赞")
    @LoginRequired
    public Result bookLike(HttpServletRequest request, Integer bookId) {
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        bookService.bookLike(bookId, userId);
        return Result.success();
    }

    @GetMapping("bookCollect")
    @ApiOperation(value = "收藏/取消收藏")
    @LoginRequired
    public Result bookCollect(HttpServletRequest request, Integer bookId) {
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        bookService.bookCollect(bookId, userId);
        return Result.success();
    }

    @GetMapping("bookCollectList")
    @ApiOperation(value = "收藏列表")
    @LoginRequired
    public Result bookCollectList(HttpServletRequest request) {
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        return Result.success(bookService.bookCollectList(userId));
    }

    /**
     * 小说打赏列表
     * @param bookId
     */
    @GetMapping("userSendList")
    @ApiOperation(value = "打赏列表")
    @LoginRequired
    public Result userSendList(Integer pageNumber, Integer pageSize, Integer bookId) {
        return Result.success(bookService.userSendList(pageNumber, pageSize, bookId));
    }

    @GetMapping("comment")
    @ApiOperation(value = "评论")
    @LoginRequired
    public Result comment(HttpServletRequest request, Integer bookId, String commentInfo) {
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        User user = userMapper.findUserById(userId);
        bookService.insertComment(user, bookId, commentInfo);
        return Result.success();
    }

    @GetMapping("commentList")
    @ApiOperation(value = "评论列表")
    public Result commentList(Integer pageNumber, Integer pageSize, Integer bookId) {
        return Result.success(bookService.commentList(pageNumber, pageSize, bookId));
    }

    @GetMapping("categoryList")
    @ApiOperation(value = "大分类列表")
    public Result categoryList() {
        return Result.success(bookService.categoryList());
    }

    @GetMapping("categoryPageList")
    @ApiOperation(value = "分类小说分页列表")
    public Result categoryPageList(Integer pageNumber, Integer pageSize, Integer category) {
        return Result.success(bookService.categoryPageList(pageNumber, pageSize, category));
    }

    /**
     * 漫画排行
     * @param bookParams
     */
    @GetMapping("ranking")
    @ApiOperation(value = "小说排行")
    public Result ranking(BookParams bookParams) {
        return bookService.ranking(bookParams);
    }

}

