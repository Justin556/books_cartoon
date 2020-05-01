package com.app.books.service.serviceImpl;

import com.app.books.config.AuthenticationInterceptor;
import com.app.books.entity.*;
import com.app.books.mapper.ChapterMapper;
import com.app.books.mapper.UserMapper;
import com.app.books.pojo.BookDetailsPojo;
import com.app.books.pojo.ComicDetailsPojo;
import com.app.books.service.ChapterService;
import com.app.books.utils.RedisUtil;
import com.app.books.vo.ChapterQuery;
import com.app.books.vo.ComicQuery;
import com.app.books.mapper.ComicMapper;
import com.app.books.result.Result;
import com.app.books.service.ComicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicMapper comicMapper;

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Result comicList(ComicQuery comicQuery) {
        if(comicQuery.getCategory()!=null&&comicQuery.getCategory()!=""&&comicQuery.getCategory().equals("全部")){
            comicQuery.setCategory(null);
        }
        PageHelper.startPage(comicQuery.getPageNumber(),comicQuery.getPageSize());//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<Comic> list = comicMapper.findAll(comicQuery);
        PageInfo<Comic> pageInfo = new PageInfo<Comic>(list);
        return Result.success(pageInfo);
    }
    @Override
    public Result ranking(ComicQuery comicQuery) {
        PageHelper.startPage(comicQuery.getPageNumber(),comicQuery.getPageSize());//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<Comic> list = comicMapper.ranking();
        PageInfo<Comic> pageInfo = new PageInfo<Comic>(list);
        return Result.success(pageInfo);
    }

    @Override
    public Result details(String comicId) {
        ComicDetailsPojo comicDetailsPojo = comicMapper.details(comicId);
        comicDetailsPojo.setSendList(comicMapper.userSendList(comicId));
        comicDetailsPojo.setSendSum(comicMapper.userSendMoneyList(comicId));
        comicDetailsPojo.setCommentList(comicMapper.commentList(comicId));
        comicDetailsPojo.setCommentSum(comicDetailsPojo.getCommentList().size());
        comicDetailsPojo.setComicEpisodes(comicMapper.comicEpisodeList(comicId));
        comicDetailsPojo.setChapterSum(comicDetailsPojo.getComicEpisodes().size());
        ChapterQuery chapterQuery=null;
        if(authenticationInterceptor.userId!="null"&&!authenticationInterceptor.userId.equals("null")){
            comicDetailsPojo.setLikeStatus(comicMapper.likeStatus(authenticationInterceptor.userId,comicId));
            comicDetailsPojo.setCollectStatus(comicMapper.collectStatus(authenticationInterceptor.userId,comicId));
            chapterQuery=chapterService.selectChapter(comicId,2);
        }


        if(chapterQuery==null){
            chapterQuery=new ChapterQuery();
        }
        comicDetailsPojo.setChapterQuery(chapterQuery);
        return Result.success(comicDetailsPojo);
    }

    @Override
    public Result homePage(ComicQuery comicQuery) {
        Map<String, List<Comic>> map =new HashMap<>();
        comicQuery.setCategory("");
        PageHelper.startPage(1,6);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<Comic> list=comicMapper.findAll(comicQuery);
        map.put("今日推荐",list);

        comicQuery.setCategory("后宫");
        PageHelper.startPage(1,6);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        list=comicMapper.findAll(comicQuery);
        map.put("猜你喜欢",list);

        comicQuery.setCategory("热血");
        PageHelper.startPage(1,6);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        list = comicMapper.findAll(comicQuery);
        map.put("男生喜欢",list);

        comicQuery.setCategory("恋爱,古风");
        PageHelper.startPage(1,6);//这行是重点，表示从pageNum页开始，每页pageSize条数据
        list=comicMapper.findAll(comicQuery);
        map.put("女生喜欢",list);



        return Result.success(map);
    }

    @Override
    public Result bannerDetails(String comicId) {
        ChapterQuery chapterQuery=new ChapterQuery();
        ComicEpisodes comicEpisodes=comicMapper.getEpisodeById(comicId);
        ComicDetailsPojo comic= comicMapper.details(comicEpisodes.getComicId()+"");

        if(authenticationInterceptor.userId!="null"&&!authenticationInterceptor.userId.equals("null")){
            if(chapterService.selectChapter(comic.getId()+"",2)==null){
                chapterQuery.setOutId(comic.getId());
                chapterQuery.setType(2);
                chapterQuery.setUserId(Integer.parseInt(authenticationInterceptor.userId));
                chapterQuery.setChapter(comicEpisodes.getTitle());
                chapterQuery.setChapterId(Integer.parseInt(comicId));
                chapterService.addChapter(chapterQuery);
            }else{
                chapterQuery.setOutId(comic.getId());
                chapterQuery.setType(2);
                chapterQuery.setUserId(Integer.parseInt(authenticationInterceptor.userId));
                chapterQuery.setChapter(comicEpisodes.getTitle());
                chapterQuery.setChapterId(Integer.parseInt(comicId));
                chapterMapper.update(chapterQuery);
            }
        }

        Integer money = comicMapper.getEpisodeBy(comicId).getMoney().intValue();//阅读该章节需要的费用
        if (money != null || money != 0){//收费小说
            if (authenticationInterceptor.userId=="null"&&authenticationInterceptor.userId.equals("null")){
                return Result.error(200, "请登录！");
            }
            User user = userMapper.findUserById(Integer.parseInt(authenticationInterceptor.userId));
            if (user.getIsVip() == 0){//如果不是vip
                String moneyStut= comicMapper.getIsPay(user.getId(), comicId);
                if (moneyStut==null){//如果本章节没付过费
                    if (user.getBookCurrency() < money){//如果用户的书币不足以支付该章节费用
                        return Result.error(200, "书币不足！");
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

                        ComicIsPay comicIsPay = new ComicIsPay(user.getId(), Integer.parseInt(comicId), 1);
                        comicMapper.addComicIsPay(comicIsPay);//标记该章节为已付费
                    }
                }
            }
        }

        return Result.success(comicMapper.bannerDetails(comicId));
    }

    @Override
    public Result addComicLikes(ComicLikes comicLikes) {
        if (comicMapper.getLikeIdByComicIdIdAndUserId(comicLikes.getComicId(), Integer.parseInt(authenticationInterceptor.userId)) == null) {//未点赞
            comicLikes.setUserId(Integer.parseInt(authenticationInterceptor.userId));
            comicMapper.insertComicLike(comicLikes);
        }else{
            //如果已点赞则取消点赞
            comicMapper.deleteComicLike(comicLikes.getComicId(), Integer.parseInt(authenticationInterceptor.userId));
        }
        return  Result.success();
    }

    @Override
    public Result sendPage(ComicQuery comicQuery) {
        PageHelper.startPage(comicQuery.getPageNumber(),comicQuery.getPageSize());//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<UserSendLog> list = comicMapper.userSendList(comicQuery.getComicId());
        PageInfo<UserSendLog> pageInfo = new PageInfo<UserSendLog>(list);
        return Result.success(pageInfo);
    }

    @Override
    public Result commentPage(ComicQuery comicQuery) {
        PageHelper.startPage(comicQuery.getPageNumber(),comicQuery.getPageSize());//这行是重点，表示从pageNum页开始，每页pageSize条数据
        List<Comment> list = comicMapper.commentList(comicQuery.getComicId());
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(list);
        return Result.success(pageInfo);
    }



    @Override
    public Result closedComic(ComicCollect comicCollect) {
        comicCollect.setUserId(Integer.parseInt(authenticationInterceptor.userId));
        if (comicMapper.getCollectIdByComicIdIdAndUserId(comicCollect.getComicId(), Integer.parseInt(authenticationInterceptor.userId)) == null) {//未收藏
            comicMapper.insertComicCollect(comicCollect);
        }else{
            //如果已收藏则取消收藏
            comicMapper.deleteComicCollect(comicCollect.getComicId(), comicCollect.getUserId());
        }
        return Result.success();
    }

    @Override
    public Result userSend(UserSendLog userSendLog) {
        return Result.success(comicMapper.userSend(userSendLog));
    }

    @Override
    public Result readingHistory(ComicQuery comicQuery) {
        return null;
    }

    @Override
    public Result continueSee(ComicQuery comicQuery) {
        return null;
    }

    @Override
    public Result closedHistory(ComicCollect comicCollect) {
        comicCollect.setUserId(Integer.parseInt(authenticationInterceptor.userId));
        return Result.success(comicMapper.closedHistory(comicCollect));
    }

    @Override
    public Result historicalRecord() {

        return Result.success(comicMapper.historicalRecord(authenticationInterceptor.userId));
    }

    @Override
    public Result comictype() {
        return Result.success(comicMapper.comictype());
    }
}
